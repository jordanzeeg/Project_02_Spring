
package com.java.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.dto.Friend;
import com.java.service.FriendService;

@RestController
@RequestMapping("/friends")
@CrossOrigin(origins = "http://localhost:4200")
public class FriendController {
	@Autowired
	FriendService service;

	@GetMapping
	@ResponseBody
	public ResponseEntity<?> getFriends() throws IOException {

		List<Friend> friends = service.getAll();

		return ResponseEntity.ok(friends); // copied from video tutorial by b2 Tech
		// return ResponseEntity.ok().body(responseString.toString());
	}

	@GetMapping("/{id}") // sets variable as part of the url

	public ResponseEntity<?> getFriendById(@PathVariable("id") int id) throws IOException {
		// @Pathvariable sets the variable in the url to the parameter

		Friend friend = service.get(id);
		if (friend == null) {
			return ResponseEntity.ok().body("a friend with id: " + id + " is not currently in database.");
		} else {
			return ResponseEntity.ok().body(friend.toString());
		}
	}

	@GetMapping("/={username}") // sets variable as part of the url
	public ResponseEntity<?> getFriendByUsername(@PathVariable String username) {
		// @Pathvariable sets the variable in the url to the parameter
		return ResponseEntity.ok().body(service.getByUsername(username));

		// TODO CRUD FRIENDS
		// TODO getfriendbyname
		// TODO getFriendsbyPostId
	}

//	@GetMapping("/getbypostid") //will add if we have time
//	public void getFriendByPostId(@PathVariable int postId, HttpServletResponse response) throws IOException{
//			
//	}
	@PostMapping()
	public ResponseEntity<?> SaveFriend(@RequestBody Friend friend) {
		Friend dataFriend = service.getByUsername(friend.getUsername());
		if (dataFriend.getId() == 0) {
			service.save(friend);
			String username = friend.getUsername();
			return ResponseEntity.ok().body("Friend saved with username = " + username + " id = " + friend.getId());
		} else
			return ResponseEntity.ok("Friend already in database." + dataFriend);
	}

	@PutMapping() // "/byid{id}"
	public ResponseEntity<?> UpdateFriend(@RequestBody Friend friend) throws IOException {
		// @PathVariable("id") int id,
//		if(friend.getId()!= id) {
//			return ResponseEntity.ok("friend id does not match id from path.");
//		}
		Friend dataFriend = service.getByUsername(friend.getUsername());
		if (dataFriend.getId() == 0) { // TODO ask people if null is what get actually returns
			// response.getWriter().println("friend is not currently in database. save
			// friend first");
			return ResponseEntity.ok().body("friend is not currently in database. save friend first");
		} else {
			service.update(friend);
			// response.getWriter().println("Inserted successfully");
			return ResponseEntity.ok().body("Inserted Successfully");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> DeleteFriend(@PathVariable("id") int id) {
		// assumption of a form of some kind
		Friend friend = service.get(id);
		if (friend == null) { // TODO ask people if null is what get actually returns
			return ResponseEntity.ok().body("friend not found to delete. Delete unsuccessful");
		} else {
			service.delete(friend);
			return ResponseEntity.ok().body("Deleted successfully. They weren't really our friend anyway");
		}
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Testing Testing by Poho
	 */
	@PostMapping("/register")
	public ResponseEntity<?> registerFriend(@RequestBody Friend friend) {
		Friend dataFriend = service.getUsername(friend.getUsername());
		Friend emailFriend = service.getEmail(friend.getEmail());
		if (dataFriend != null) {
			return ResponseEntity.ok().body("Username already existed. Please use a different username");
		}
		if (emailFriend != null) {
			return ResponseEntity.ok().body("Email already existed. Please use a different email");
		}
		service.save(friend);
		String username = friend.getUsername();
		return ResponseEntity.ok().body("Friend saved with username = " + username + " id = " + friend.getId());
		// }
		// else return ResponseEntity.ok("Friend already in database." + dataFriend);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginTrial(@RequestBody Friend friend) {
		Friend dataFriend = service.getUsername(friend.getUsername());// info from db
		if (dataFriend == null) {
			return ResponseEntity.ok("Login Trial Fail. Username/Password Not match");
		} else if ((friend.getUsername().equals(dataFriend.getUsername()))) {
			if (service.passwordValidation(friend.getUsername(), friend.getPassword())) {
				// String username = friend.getUsername();
				return ResponseEntity.ok().body("Login Trial Success with username: " + dataFriend.getUsername());
			}
		}
		return ResponseEntity.ok("Login Trial Fail. UserName/Password Not match");

	}
}