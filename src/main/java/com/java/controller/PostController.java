package com.java.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.dto.Messengering;
import com.java.dto.Post;
import com.java.service.PostService;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {
	@Autowired
	PostService service;

	@GetMapping
	@ResponseBody
	public ResponseEntity<?> getPosts() throws IOException {
		List<Post> posts = service.getAll();
		return ResponseEntity.ok(posts); // copied from video tutorial by b2 Tech
		// return ResponseEntity.ok().body(responseString.toString());
	}

	
	@GetMapping("/{authorId}") // pass in user id
	public ResponseEntity<?> GetPostsByUserId(@PathVariable int authorId) {
		List<Post> posts = new ArrayList<Post>();
		posts = service.getPostByAuthorId(authorId);
		return ResponseEntity.ok(posts);
	}

	@GetMapping("/{id}") // sets variable as part of the url
	public ResponseEntity<?> getPostById(@PathVariable int id) {
		// @Pathvariable sets the variable in the url to the parameter
		Messengering mess = new Messengering(1, "Post not found");
		Post post = service.get(id);
		if (post == null) {
			return ResponseEntity.ok(mess);
		} else {
			return ResponseEntity.ok(post);

		}
	}

	@GetMapping("/={title}") // sets variable as part of the url
	public void getPostByTitle(@PathVariable String title) {
		// @Pathvariable sets the variable in the url to the parameter

		List<Post> post = service.getPostByTitle(title);

		ResponseEntity.ok(post);
	}

	@PostMapping
	public ResponseEntity<?> SavePost(@RequestBody Post post) {
		Messengering mess = new Messengering(1, "Post already exists in Database");
		Messengering success = new Messengering(0, "Post not found in Database. Save successful");
		if (post.getId() == 0) {
			service.save(post);

			return ResponseEntity.ok(success);
		} else
			return ResponseEntity.ok(mess);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> UpdatePost(@RequestBody Post post) {
		// assumption of a form of some kind
		Messengering mess = new Messengering(1, "Post not found in Database");
		Messengering success = new Messengering(0, "Post updated found in Database");
		if (service.get(post.getId()).getId() == 0) {
			return ResponseEntity.ok(mess);
		} else {
			service.update(post);
			return ResponseEntity.ok(success);
		}
	}

	@DeleteMapping
	public ResponseEntity<?> DeletePost(@RequestBody Post post) {
		// assumption of a form of some kind
		Messengering mess = new Messengering(1, "Post not found in Database");
		Messengering success = new Messengering(0, "Post deleted in Database");
		if (post.getId() == 0) { 
			return ResponseEntity.ok(mess);
		} else {
			service.delete(post);
			return ResponseEntity.ok(success);
		}
	}

}
