package com.java.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.dto.Messengering;
import com.java.dto.PostLike;
import com.java.service.PostLikeService;


@Controller
@RequestMapping("/postlikes")
public class PostLikeController {
	@Autowired
	PostLikeService service;

	@GetMapping
	public  ResponseEntity<?> getPostLikes()  {
		List<PostLike> postLikes = service.getAll();
		return ResponseEntity.ok(postLikes);
	}
	@GetMapping("/{id}") // sets variable as part of the url
	public ResponseEntity<?> getPostLikeById(@PathVariable int id) { 
		PostLike postLike = new PostLike();
		postLike = service.get(id);
		return ResponseEntity.ok(postLike);
		}
	
	@GetMapping("/bypost{postId}") // sets variable as part of the url
	public ResponseEntity<?> getPostLikeByPostId(@PathVariable int postId, HttpServletResponse response) throws IOException { 
		// @Pathvariable sets the variable in the url to the parameter

		List<PostLike> postLikes = service.getLikeBasedOnPosttId(postId);
		return ResponseEntity.ok(postLikes);

	}
//	@GetMapping("/getbypostid") //will add if we have time
//	public void getPostLikeByPostId(@PathVariable int postId, HttpServletResponse response) throws IOException{
//			
//	}
	@PostMapping
	public ResponseEntity<?> SavePostLike(@RequestBody PostLike t) { 
		Messengering mess = new Messengering(1,"PostLike already exists in Database. unable to update");
		Messengering success = new Messengering(0,"Save successful");
		if(t.getId()== 0) {
		service.save(t);
		return ResponseEntity.ok(success);
		}else return ResponseEntity.ok(mess);
	}

	
	@PutMapping
	public ResponseEntity<?> UpdatePostLike(@RequestBody PostLike t) {
		Messengering mess = new Messengering(1,"PostLike does not exist in Database. unable to update");
		Messengering success = new Messengering(0,"Update successful");
		if(t.getId()== 0) {
			return ResponseEntity.ok(mess);
		}else {
		service.update(t);
		return ResponseEntity.ok(success);
		}
	}
	@DeleteMapping
	public ResponseEntity<?> DeletePostLike(@RequestBody PostLike t)  { 
		Messengering mess = new Messengering(1,"PostLike does not exist in Database. unable to update");
		Messengering success = new Messengering(0,"Delete successful");
		if(t.getId()== 0) {
			return ResponseEntity.ok(mess);
		}else {
		service.delete(t);
		return ResponseEntity.ok(success);
		}
	}
}



		//TODO CRUD PostLike
		//TODO getPostLikebyId
		//TODO getPostLikeByPost