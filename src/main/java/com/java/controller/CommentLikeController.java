package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.dto.CommentLike;
import com.java.dto.Messengering;
import com.java.service.CommentLikeService;

@Controller
@RequestMapping("/commentlikes")
@CrossOrigin(origins = "http://localhost:4200")
public class CommentLikeController {
	@Autowired 
	CommentLikeService service;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<?> getCommentLikes() {
		List<CommentLike> commentLikes = service.getAll();
		return ResponseEntity.ok(commentLikes);
	}
	@GetMapping("/{id}") // sets variable as part of the url
	public ResponseEntity<?> getCommentLikeById(@PathVariable int id){ 
		// @Pathvariable sets the variable in the url to the parameter
		CommentLike commentLike = new CommentLike();
		commentLike = service.get(id);

		return ResponseEntity.ok(commentLike);
			}
		
		
	@GetMapping("/bycomment{commentId}") // sets variable as part of the url
	public ResponseEntity<?> getCommentLikeByCommentId(@PathVariable int commentId) { 
		// @Pathvariable sets the variable in the url to the parameter
		List<CommentLike> commentLikes = service.getCommentLikeBasedOnCommentId(commentId);
		return ResponseEntity.ok(commentLikes);
		}

	

	@PostMapping
	public ResponseEntity<?> SaveCommentLike(@RequestBody CommentLike t){ 
		// assumption of a form of some kind
		Messengering mess = new Messengering(1,"CommentLike already exists in Database. unable to update");
		Messengering success = new Messengering(0,"Save successful");

		if(t.getId()== 0) {
		service.save(t);
		return ResponseEntity.ok(success);
		}else return ResponseEntity.ok(mess);
	}

	
	@PutMapping
	public ResponseEntity<?> UpdateCommentLike(@RequestBody CommentLike t) {
		// assumption of a form of some kind
		Messengering mess = new Messengering(1,"CommentLike does not exist in Database. unable to update");
		Messengering success = new Messengering(0,"CommentLike found in Database. Update successful");
		if (t.getId()== 0) {
			return ResponseEntity.ok(mess);
		}else {
		service.update(t);
			return ResponseEntity.ok(success);
		}
	}
	
	@DeleteMapping
	public ResponseEntity<?> DeleteCommentLike(@RequestBody CommentLike t) { 
		// assumption of a form of some kind
		Messengering mess = new Messengering(1,"CommentLike does not exist in Database. unable to update");
		Messengering success = new Messengering(0,"CommentLike found in Database. Delete successful");
		if (t.getId()== 0) {
			return ResponseEntity.ok(mess);
		}
		service.delete(t);
		return ResponseEntity.ok(success);
		
	}

		//TODO don't throw exception
		
		
		//TODO CRUD CommentLike
		//TODO getcommentlikebyid
		//TODO getcommentlikebycommentId
	}

