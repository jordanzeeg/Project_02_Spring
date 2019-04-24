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

import com.java.dto.PostLike;
import com.java.service.PostLikeService;


@Controller
@RequestMapping("/postlikes.do")
public class PostLikeController {
	@Autowired
	PostLikeService service;

	@GetMapping
	public void getPostLikes(HttpServletResponse response) throws IOException {
		List<PostLike> postLikes = service.getAll();
		response.getWriter().println(postLikes);
	}
	@GetMapping("/get/byid{id}") // sets variable as part of the url
	public void getPostLikeById(@PathVariable int id, HttpServletResponse response) throws IOException { 
		// @Pathvariable sets the variable in the url to the parameter

		PostLike postLike = service.get(id);
		if (postLike == null) {
			response.getWriter().println("PostLike object not found");
		} else {
			try {
				response.getWriter().println(postLike);
			} catch (IOException e) {
				response.getWriter().println("id not found");
				e.printStackTrace();
			}
		}
		}
	@GetMapping("/get/bypostid{postId}") // sets variable as part of the url
	public void getPostLikeByPostId(@PathVariable int postId, HttpServletResponse response) throws IOException { 
		// @Pathvariable sets the variable in the url to the parameter

		List<PostLike> postLikes = service.getLikeBasedOnPosttId(postId);
		if (postLikes == null) {
			response.getWriter().println("PostLikes objects not found");
		} else {
			try {
				response.getWriter().println(postLikes);
			} catch (IOException e) {
				response.getWriter().println("id not found");
				e.printStackTrace();
			}
		}

	}
//	@GetMapping("/getbypostid") //will add if we have time
//	public void getPostLikeByPostId(@PathVariable int postId, HttpServletResponse response) throws IOException{
//			
//	}
	@PostMapping("/save")
	public void SavePostLike(@Valid @ModelAttribute PostLike postLike, BindingResult result, HttpServletResponse response) throws IOException { 
		// assumption of a form of some kind
		if (result.hasErrors()) {
			response.getWriter().println("Inserted unsuccessfully");
		}
		service.save(postLike);
		response.getWriter().println("Inserted successfully");
	}

	
	@PostMapping("/update")
	public void UpdatePostLike(@Valid @ModelAttribute PostLike postLike, BindingResult result, HttpServletResponse response) throws IOException {
		// assumption of a form of some kind
		if (result.hasErrors()) {
			response.getWriter().println("Inserted unsuccessfully");
		}
		else if(service.get(postLike.getId())== null) { //TODO ask people if null is what get actually returns
			response.getWriter().println("postLike is not currently in database. save postLike first");
		}else
		{
		service.update(postLike);
		response.getWriter().println("Inserted successfully");
		}
	}
	@PostMapping("/delete")
	public void DeletePostLike(@Valid @ModelAttribute PostLike postLike, BindingResult result, HttpServletResponse response) throws IOException { 
		// assumption of a form of some kind
		if (result.hasErrors()) {
			response.getWriter().println("Deleted unsuccessfully");
		}
		else if(service.get(postLike.getId())== null) { //TODO ask people if null is what get actually returns
			response.getWriter().println("postLike is not currently in database. create postLike first");
		}else
		{
		service.delete(postLike);
		response.getWriter().println("Deleted successfully. They weren't really our postLike anyway");
		}
	}
}



		//TODO CRUD PostLike
		//TODO getPostLikebyId
		//TODO getPostLikeByPost