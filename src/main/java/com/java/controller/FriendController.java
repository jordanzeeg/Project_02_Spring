package com.java.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
