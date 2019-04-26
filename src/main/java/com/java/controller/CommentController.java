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
import com.java.dto.Messengering;
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
	@GetMapping("/bypost{id}")
	public ResponseEntity<?> getCommentsByPostId(@PathVariable("id") int id) {
		Messengering mess = new Messengering(5,"No comments found with post id:"+ id);
		List<Comment> commentList = service.getCommentByPostId(id);
		if (commentList == null) {
			return ResponseEntity.ok(mess);
		} else {
			return ResponseEntity.ok(commentList);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCommentsById(@PathVariable("id") int id) {
		Messengering mess = new Messengering(1,"No comments found with id:"+ id);
		Comment comment = service.get(id);
		if(comment == null) {
			return ResponseEntity.ok(mess);
		} else {
			return ResponseEntity.ok(comment);
		}
	}

	@PostMapping
	public ResponseEntity<?> saveComment(@RequestBody Comment comment) {
		Messengering mess = new Messengering(1,"Comment already exists in Database");
		Messengering success = new Messengering(0,"Comment not found in Database. Save successful with id: ");
		if(comment.getId()==0) {
		service.save(comment);
		success.setMenagerie(success.getMenagerie() + comment.getId());
		return ResponseEntity.ok(success);
		}else 
			return ResponseEntity.ok(mess);

	}


	@PutMapping
	public ResponseEntity<?> updateComment(@RequestBody Comment comment) {
		Messengering mess = new Messengering(1,"Comment doesn't exist in Database");
		Messengering success = new Messengering(0,"Comment exists in Database. update successful with id: ");
		if(comment.getId()!=0) {
		service.update(comment);
		success.setMenagerie(success.getMenagerie() + comment.getId());
		return ResponseEntity.ok(success);
		}else 
			return ResponseEntity.ok(mess);
	}




	@DeleteMapping
	public ResponseEntity<?> deleteComment(@RequestBody Comment comment) {
		Messengering mess = new Messengering(1,"Comment doesn't exist in Database");
		Messengering success = new Messengering(0,"Comment exists in Database. update successful with id: ");
		if(comment.getId() == service.get(comment.getId()).getId()) {
		service.delete(comment);
		success.setMenagerie(success.getMenagerie() + comment.getId());
		return ResponseEntity.ok(success);
		}else 
			return ResponseEntity.ok(mess);
	}
}
