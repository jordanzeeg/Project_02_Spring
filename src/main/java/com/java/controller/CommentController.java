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


import com.java.dto.Comment;
import com.java.dto.Friend;
import com.java.service.CommentService;


@Controller
@RequestMapping("/comments")
public class CommentController {
	@Autowired
	CommentService service;

	@GetMapping
	public void GetAllComments(HttpServletResponse response) throws IOException {
		List<Comment> comments = service.getAll();
		response.getWriter().println(comments);
	}
	@GetMapping("/getcommentsbypost{id}") // pass in user id
	public void GetCommentsByPostId(@PathVariable int postId, HttpServletResponse response) throws IOException {
		List<Comment> comments = service.getCommentByPostId(postId);
		response.getWriter().println(comments);
	}

	@GetMapping("/get/byid{id}") // sets variable as part of the url
	public void getCommentById(@PathVariable int id, HttpServletResponse response) throws IOException {
		// @Pathvariable sets the variable in the url to the parameter

		Comment comment = service.get(id);
		if (comment == null) {
			response.getWriter().println("Comment object not found");
		} else {
			try {
				response.getWriter().println(comment);
			} catch (IOException e) {
				response.getWriter().println("id not found");
				e.printStackTrace();
			}
		}
	}

	// TODO don't throw exception

	// TODO CRUD FRIENDS
	// TODO getcommentbyname
	// TODO getCommentsbyCommentId

//		@GetMapping("/getbycommentid") //will add if we have time
//		public void getCommentByCommentId(@PathVariable int commentId, HttpServletResponse response) throws IOException{
//				
//		}
	@PostMapping("/save")
	public void SaveComment(@Valid @ModelAttribute Comment comment, BindingResult result, HttpServletResponse response)
			throws IOException {
		// assumption of a form of some kind
		if (result.hasErrors()) {
			response.getWriter().println("Inserted unsuccessfully");
		}
		service.save(comment);
		response.getWriter().println("Inserted successfully");
	}

	@PostMapping("/update")
	public void UpdateComment(@Valid @ModelAttribute Comment comment, BindingResult result,
			HttpServletResponse response) throws IOException {
		// assumption of a form of some kind
		if (result.hasErrors()) {
			response.getWriter().println("Inserted unsuccessfully");
		} else if (service.get(comment.getId()) == null) { // TODO ask people if null is what get actually returns
			response.getWriter().println("comment is not currently in database. save comment first");
		} else {
			service.update(comment);
			response.getWriter().println("Inserted successfully");
		}
	}

	@PostMapping("/delete")
	public void DeleteComment(@Valid @ModelAttribute Comment comment, BindingResult result,
			HttpServletResponse response) throws IOException {
		// assumption of a form of some kind
		if (result.hasErrors()) {
			response.getWriter().println("Deleted unsuccessfully");
		} else if (service.get(comment.getId()) == null) { // TODO ask people if null is what get actually returns
			response.getWriter().println("comment is not currently in database. create comment first");
		} else {
			service.delete(comment);
			response.getWriter().println("Deleted successfully. It wasn't a good comment anyway");
		}
	}

	// TODO don't throw exception

	// TODO CRUD Comments
	// TODO getcommentsbyid
	// TODO getcommentsbypostid
}
