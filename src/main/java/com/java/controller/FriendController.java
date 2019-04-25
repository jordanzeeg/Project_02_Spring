package com.java.controller;

import java.io.IOException;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.java.service.FriendService;

@RestController
@RequestMapping("/friends")
public class FriendController {
	@Autowired
	FriendService service;

	
	@GetMapping
	@ResponseBody
	public ResponseEntity<?> getFriends() throws IOException {
		
		List<Friend> friends = service.getAll();

		return ResponseEntity.ok(friends); //copied from video tutorial by b2 Tech
		//return ResponseEntity.ok().body(responseString.toString()); 
	}
	
	@GetMapping("/byid{id}") // sets variable as part of the url
	
	public ResponseEntity<?> getFriendById(@PathVariable("id") int id) throws IOException { 
		// @Pathvariable sets the variable in the url to the parameter
		Messengering mess = new Messengering("A friend with that ID was not found");
		Friend friend = service.get(id);
		if (friend == null) {
			return ResponseEntity.ok().body(mess);
		} else {
			return ResponseEntity.ok().body(friend);
		}
		}
	@GetMapping("/byusername{username}") // sets variable as part of the url
	public ResponseEntity<?> getFriendByUsername(@PathVariable String username) { 
		// @Pathvariable sets the variable in the url to the parameter
		Messengering mess = new Messengering("A friend with that Username was not found");
		Friend friend = service.getByUsername(username);
		if (friend.getUsername() == null) {
			return ResponseEntity.ok().body(mess);
		} else {
			return ResponseEntity.ok().body(friend);
		}
		

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
		Messengering mess = new Messengering("This friend is already in database");
		Messengering success = new Messengering("Save Successful");
		Friend dataFriend = service.getByUsername(friend.getUsername());
		if(dataFriend.getId()== 0) {
		service.save(friend);
		String username = friend.getUsername();
		return ResponseEntity.ok().body(success);
	}
		else return ResponseEntity.ok(mess);
	}

	
	@PutMapping() //  "/byid{id}"
	public ResponseEntity<?> UpdateFriend(@RequestBody Friend friend) throws IOException {
		Messengering mess = new Messengering("This friend is not currently in database");
		Messengering success = new Messengering("Update Successful");
		Friend dataFriend = service.getByUsername(friend.getUsername()); 
		 if(dataFriend.getId() == 0) { //TODO ask people if null is what get actually returns
			return ResponseEntity.ok().body(mess);
		}else
		{
		service.update(friend);
		//response.getWriter().println("Inserted successfully");
		return ResponseEntity.ok().body(success);
		}
	}
	@DeleteMapping("/byid{id}")
	public ResponseEntity<?> DeleteFriend(@PathVariable("id") int id) { 
		Messengering mess = new Messengering("This friend is not currently in database");
		Messengering success = new Messengering("Delete Successful");
		Friend friend = service.get(id);
		if(friend == null) { //TODO ask people if null is what get actually returns
			return ResponseEntity.ok().body(mess);
		}else
		{
		service.delete(friend);
		return ResponseEntity.ok().body(success);
		}
	}
	
	/*
	 * 
	 *  
	 *  
	 *  
	 *  
	 *  
	 *  Testing Testing by Poho */
	@PostMapping("/register")
	public ResponseEntity<?> registerFriend(@RequestBody Friend friend) {
		Messengering mess = new Messengering("Username already existed. Please use a different username");
		Messengering mess2 = new Messengering("Email already existed. Please use a different email");
		Messengering success = new Messengering("Save Successful");
		Friend dataFriend = service.getUsername(friend.getUsername());
		Friend emailFriend = service.getEmail(friend.getEmail());
		if (dataFriend != null) {
			return ResponseEntity.ok().body(mess);
		}
		if (emailFriend != null) {
			return ResponseEntity.ok().body(mess2);
		}
		service.save(friend);
		String username = friend.getUsername();
		return ResponseEntity.ok().body(success);
		// }
		// else return ResponseEntity.ok("Friend already in database." + dataFriend);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginTrial(@RequestBody Friend friend) {
		Messengering mess = new Messengering("Login Trial Fail. UserName/Password Not match");
		Messengering mess2 = new Messengering("Login Trial Fail. UserName/Password Not match");
		Messengering success = new Messengering("Login Successful");
		Friend dataFriend = service.getUsername(friend.getUsername());// info from db
		if (dataFriend == null) {
			return ResponseEntity.ok(mess);
		} else if ((friend.getUsername().equals(dataFriend.getUsername()))) {
			if (service.passwordValidation(friend.getUsername(), friend.getPassword())) {
				// String username = friend.getUsername();
				return ResponseEntity.ok().body(success);
			}
		}
		return ResponseEntity.ok(mess2);

	}
}

