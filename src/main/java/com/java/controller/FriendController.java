package com.java.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.java.dto.Friend;
import com.java.service.FriendService;

@Controller
@RequestMapping("/friends.do")
public class FriendController {
	@Autowired
	FriendService service;

	@GetMapping
	public void getFriends(HttpServletResponse response) throws IOException {
		List<Friend> friends = service.getAll();
		response.getWriter().println(friends);
	}
	@GetMapping("/get/byid{id}") // sets variable as part of the url
	public void getFriendById(@PathVariable int id, HttpServletResponse response) throws IOException { 
		// @Pathvariable sets the variable in the url to the parameter

		Friend friend = service.get(id);
		if (friend == null) {
			response.getWriter().println("Friend object not found");
		} else {
			try {
				response.getWriter().println(friend);
			} catch (IOException e) {
				response.getWriter().println("id not found");
				e.printStackTrace();
			}
		}
		}
	@GetMapping("/get/byusername{username}") // sets variable as part of the url
	public void getFriendByUsername(@PathVariable String username, HttpServletResponse response) throws IOException { 
		// @Pathvariable sets the variable in the url to the parameter

		Friend friend = service.getByUsername(username);
		if (friend == null) {
			response.getWriter().println("Friend object not found");
		} else {
			try {
				response.getWriter().println(friend);
			} catch (IOException e) {
				response.getWriter().println("id not found");
				e.printStackTrace();
			}
		}
		// TODO don't throw exception

		// TODO CRUD FRIENDS
		// TODO getfriendbyname
		// TODO getFriendsbyPostId
	}
//	@GetMapping("/getbypostid") //will add if we have time
//	public void getFriendByPostId(@PathVariable int postId, HttpServletResponse response) throws IOException{
//			
//	}
	@PostMapping("/save")
	public void SaveFriend(@Valid @ModelAttribute Friend friend, BindingResult result, HttpServletResponse response) throws IOException { 
		// assumption of a form of some kind
		if (result.hasErrors()) {
			response.getWriter().println("Inserted unsuccessfully");
		}
		service.save(friend);
		response.getWriter().println("Inserted successfully");
	}

	
	@PostMapping("/update")
	public void UpdateFriend(@Valid @ModelAttribute Friend friend, BindingResult result, HttpServletResponse response) throws IOException {
		// assumption of a form of some kind
		if (result.hasErrors()) {
			response.getWriter().println("Inserted unsuccessfully");
		}
		else if(service.get(friend.getId())== null) { //TODO ask people if null is what get actually returns
			response.getWriter().println("friend is not currently in database. save friend first");
		}else
		{
		service.update(friend);
		response.getWriter().println("Inserted successfully");
		}
	}
	@PostMapping("/delete")
	public void DeleteFriend(@Valid @ModelAttribute Friend friend, BindingResult result, HttpServletResponse response) throws IOException { 
		// assumption of a form of some kind
		if (result.hasErrors()) {
			response.getWriter().println("Deleted unsuccessfully");
		}
		else if(service.get(friend.getId())== null) { //TODO ask people if null is what get actually returns
			response.getWriter().println("friend is not currently in database. create friend first");
		}else
		{
		service.delete(friend);
		response.getWriter().println("Deleted successfully. They weren't really our friend anyway");
		}
	}
}


