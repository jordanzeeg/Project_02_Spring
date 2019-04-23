package com.java.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import com.java.dto.Friend;
import com.java.service.FriendService;

@Controller
@RequestMapping("/friends")
public class FriendController {
	@Autowired
	FriendService service;

	@GetMapping
	public ResponseEntity<List<Friend>> getFriends(HttpServletResponse response) throws IOException {
		List<Friend> friends = service.getAll();
		// response.getWriter().println(friends); //remove this when finished
		return ResponseEntity.ok().body(friends); //copied from video tutorial by b2 Tech
	}
	@GetMapping("/byid{id}") // sets variable as part of the url
	public ResponseEntity<?> getFriendById(@PathVariable("id") int id) throws IOException { 
		// @Pathvariable sets the variable in the url to the parameter

		Friend friend = service.get(id);
		if (friend == null) {
			return ResponseEntity.ok().body("a friend with id: " + id+ "is not currently in database.");
		} else {
			return ResponseEntity.ok().body(friend);
		}
		}
	@GetMapping("/byusername{username}") // sets variable as part of the url
	public ResponseEntity<?> getFriendByUsername(@PathVariable String username) { 
		// @Pathvariable sets the variable in the url to the parameter

		Friend friend = service.getByUsername(username);
		if (friend == null) {
			return ResponseEntity.ok().body("a friend with username: " + username+ "is not currently in database.");
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
	@PostMapping("/save")
	public ResponseEntity<?> SaveFriend(@RequestBody Friend friend) { 
		
		service.save(friend);
		return ResponseEntity.ok().body("Friend saved");
	}

	
	@PutMapping("/updatebyid{id}")
	public ResponseEntity<?> UpdateFriend(@PathVariable("id") int id,@RequestBody Friend friend) throws IOException {

		if(service.get(friend.getId())== null) { //TODO ask people if null is what get actually returns
			//response.getWriter().println("friend is not currently in database. save friend first");
			return ResponseEntity.ok().body("friend is not currently in database. save friend first");
		}else
		{
		service.update(friend);
		//response.getWriter().println("Inserted successfully");
		return ResponseEntity.ok().body("Inserted Successfully");
		}
	}
	@DeleteMapping("/deletebyid{id}")
	public ResponseEntity<?> DeleteFriend(@PathVariable("id") int id) { 
		// assumption of a form of some kind
		Friend friend = service.get(id);
		if(friend == null) { //TODO ask people if null is what get actually returns
			return ResponseEntity.ok().body("friend not found to delete. Delete unsuccessful");
		}else
		{
		service.delete(friend);
		return ResponseEntity.ok().body("Deleted successfully. They weren't really our friend anyway");
		}
	}
}


