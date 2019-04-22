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
import com.java.dto.CommentLike;
import com.java.service.CommentLikeService;
import com.java.service.FriendService;

@Controller
@RequestMapping("/commentlikes.do")
public class CommentLikeController {
	@Autowired 
	CommentLikeService service;
	
	@GetMapping
	public void getCommentLikes(HttpServletResponse response) throws IOException {
		List<CommentLike> commentLikes = service.getAll();
		response.getWriter().println(commentLikes);
	}
	@GetMapping("/get/byid{id}") // sets variable as part of the url
	public void getCommentLikeById(@PathVariable int id, HttpServletResponse response) throws IOException { 
		// @Pathvariable sets the variable in the url to the parameter

		CommentLike commentLike = service.get(id);
		if (commentLike == null) {
			response.getWriter().println("CommentLike object not found");
		} else {
			try {
				response.getWriter().println(commentLike);
			} catch (IOException e) {
				response.getWriter().println("id not found");
				e.printStackTrace();
			}
		}
		}
	@GetMapping("/get/bycommentid{commentId}") // sets variable as part of the url
	public void getCommentLikeByCommentId(@PathVariable int commentId, HttpServletResponse response) throws IOException { 
		// @Pathvariable sets the variable in the url to the parameter

		List<CommentLike> commentLikes = service.getLikeBasedOnCommentId(commentId);
		if (commentLikes == null) {
			response.getWriter().println("CommentLikes objects not found");
		} else {
			try {
				response.getWriter().println(commentLikes);
			} catch (IOException e) {
				response.getWriter().println("id not found");
				e.printStackTrace();
			}
		}

	}
//	@GetMapping("/getbypostid") //will add if we have time
//	public void getCommentLikeByPostId(@PathVariable int postId, HttpServletResponse response) throws IOException{
//			
//	}
	@PostMapping("/save")
	public void SaveCommentLike(@Valid @ModelAttribute CommentLike commentLike, BindingResult result, HttpServletResponse response) throws IOException { 
		// assumption of a form of some kind
		if (result.hasErrors()) {
			response.getWriter().println("Inserted unsuccessfully");
		}
		service.save(commentLike);
		response.getWriter().println("Inserted successfully");
	}

	
	@PostMapping("/update")
	public void UpdateCommentLike(@Valid @ModelAttribute CommentLike commentLike, BindingResult result, HttpServletResponse response) throws IOException {
		// assumption of a form of some kind
		if (result.hasErrors()) {
			response.getWriter().println("Inserted unsuccessfully");
		}
		else if(service.get(commentLike.getId())== null) { //TODO ask people if null is what get actually returns
			response.getWriter().println("commentLike is not currently in database. save commentLike first");
		}else
		{
		service.update(commentLike);
		response.getWriter().println("Inserted successfully");
		}
	}
	@PostMapping("/delete")
	public void DeleteCommentLike(@Valid @ModelAttribute CommentLike commentLike, BindingResult result, HttpServletResponse response) throws IOException { 
		// assumption of a form of some kind
		if (result.hasErrors()) {
			response.getWriter().println("Deleted unsuccessfully");
		}
		else if(service.get(commentLike.getId())== null) { //TODO ask people if null is what get actually returns
			response.getWriter().println("commentLike is not currently in database. create commentLike first");
		}else
		{
		service.delete(commentLike);
		response.getWriter().println("Deleted successfully. They weren't really our commentLike anyway");
		}
	}

		//TODO don't throw exception
		
		
		//TODO CRUD CommentLike
		//TODO getcommentlikebyid
		//TODO getcommentlikebycommentId
	}

