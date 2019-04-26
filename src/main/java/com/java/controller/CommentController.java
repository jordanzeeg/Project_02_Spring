package com.java.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Path;
import javax.validation.Valid;

import com.java.dto.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import com.java.dto.Comment;
import com.java.dto.Friend;
import com.java.service.CommentService;


@Controller
@RequestMapping("/comments")
public class CommentController {
	@Autowired
	CommentService service;

	@GetMapping
	public ResponseEntity<?> getComments() {
		List<Comment> commentList = service.getAll();
		return ResponseEntity.ok(commentList);
	}
	@GetMapping("/getcommentsbypost/{id}")
	public ResponseEntity<?> getCommentsByPostId(@PathVariable("id") int id) {
		List<Comment> commentList = service.getCommentByPostId(id);
		if (commentList == null) {
			return ResponseEntity.ok("No comments found with post id: " + id);
		} else {
			return ResponseEntity.ok(commentList);
		}
	}

	@GetMapping("/getcommentbyid/{id}")
	public ResponseEntity<?> getCommentsById(@PathVariable("id") int id) {
		Comment comment = service.get(id);
		if(comment == null) {
			return ResponseEntity.ok("No comment found with id: " + id);
		} else {
			return ResponseEntity.ok(comment);
		}
	}

	@PostMapping()
	public ResponseEntity<?> saveComment(@RequestBody Comment comment) {
		service.save(comment);
		return ResponseEntity.ok("Comment saved with id: " + comment.getId());

	}


	@PostMapping("/updatecomment")
	public ResponseEntity<?> updateComment(@RequestBody Comment comment) {
		service.update(comment);
		return ResponseEntity.ok("Comment updated");
	}




	@PostMapping("/deletecomment/{id}")
	public ResponseEntity<?> deleteComment(@PathVariable("id") int id) {
		Comment comment = service.get(id);
		service.delete(comment);
		return ResponseEntity.ok("Comment deleted with id: " + id);
	}
}
