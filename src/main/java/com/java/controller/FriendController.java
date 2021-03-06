
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
import com.java.dto.Messengering;
import com.java.dto.Uuidclass;
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
		Messengering mess = new Messengering(1, "Friend currently is not in database");

		Friend friend = service.get(id);
		if (friend == null) {
			return ResponseEntity.ok().body(mess);
		} else {
			return ResponseEntity.ok().body(friend.toString());
		}
	}

	@GetMapping("/={username}") // sets variable as part of the url
	public ResponseEntity<?> getFriendByUsername(@PathVariable String username) {
		// @Pathvariable sets the variable in the url to the parameter
		return ResponseEntity.ok().body(service.getByUsername(username));
	}

	@GetMapping("/search/={param}")
	public ResponseEntity<?> searchFriend(@PathVariable String param){
		return ResponseEntity.ok().body(service.search(param));
	}

	@GetMapping("/reset/{resetKey}")
	public ResponseEntity<?> getFriendByResetKey(@PathVariable String resetKey){
		return ResponseEntity.ok(service.getByResetKey(resetKey));
	}
	
//	@GetMapping("/getbypostid") //will add if we have time
//	public void getFriendByPostId(@PathVariable int postId, HttpServletResponse response) throws IOException{
//			
//	}

	@PostMapping()
	public ResponseEntity<?> SaveFriend(@RequestBody Friend friend) {
		Messengering mess = new Messengering(7, "Friend already exists in Database");
		Messengering success = new Messengering(0, "Friend not found in Database. Save successful");
		Uuidclass uuid = new Uuidclass();
		Friend dataFriend = service.getByUsername(friend.getUsername());
		if (dataFriend.getId() == 0) {
			uuid.setFriend(friend);
			service.save(friend);
			
			return ResponseEntity.ok().body(success);
		} else
			return ResponseEntity.ok(mess);
	}

	@PutMapping() // "/byid{id}"
	public ResponseEntity<?> UpdateFriend(@RequestBody Friend friend) throws IOException {
		Messengering mess = new Messengering(7, "Friend not found in Database");
		Messengering success = new Messengering(0, "Friend exists in Database. Update successful");
		Friend dataFriend = service.getByUsername(friend.getUsername());
		if (dataFriend.getId() == 0) {
			return ResponseEntity.ok().body(mess);
		} else {
			service.update(friend);
			return ResponseEntity.ok().body(success);
		}
	}

	@DeleteMapping
	public ResponseEntity<?> DeleteFriend(@RequestBody Friend friend) {
		Messengering mess = new Messengering(1, "Friend not found in Database");
		Messengering success = new Messengering(0, "Friend exists in Database. Delete successful");
		if (friend.getId() != service.get(friend.getId()).getId()) { // checks if friend is in database by id
			return ResponseEntity.ok().body(mess);
		} else {
			service.delete(friend);
			return ResponseEntity.ok().body(success);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerFriend(@RequestBody Friend friend) {
		Messengering mess = new Messengering(7, "Username already existed. Please use a different username");
		Messengering mess2 = new Messengering(2, "Email already existed. Please use a different username");
		Messengering success = new Messengering(0, "Friend now exists in Database. Registration successful");
		Friend dataFriend = service.getUsername(friend.getUsername());
		Friend emailFriend = service.getEmail(friend.getEmail());
		if (dataFriend.getId()!= 0) {
			return ResponseEntity.ok().body(mess);
		}
		if (emailFriend != null) {
			return ResponseEntity.ok().body(mess2);
		}
		service.save(friend);
		return ResponseEntity.ok().body(success);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginTrial(@RequestBody Friend friend) {

		Messengering mess = new Messengering(7, "Login Failed. UserName/Password Not match");
		Messengering success = new Messengering(0, "Login successful");

		Friend dataFriend = service.getUsername(friend.getUsername());// info from db
		if (dataFriend.getId()==0) {
			return ResponseEntity.ok(mess);
		}

		if (service.passwordValidation(friend.getUsername(), friend.getPassword())) {
			return ResponseEntity.ok().body(success);
		}

		return ResponseEntity.ok(mess);

	}
	
}