package com.java.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.dto.Post;
import com.java.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
	@Autowired
	PostService service;

	// TODO don't throw exception

	@GetMapping
	@ResponseBody
	public ResponseEntity<?> getPosts() throws IOException {

		List<Post> posts = service.getAll();

		return ResponseEntity.ok(posts); // copied from video tutorial by b2 Tech
		// return ResponseEntity.ok().body(responseString.toString());
	}


	// TODO don't throw exception
	@GetMapping("/getpostsbyuser{authorId}") // pass in user id

	public ResponseEntity<?> GetPostsByUserId(@PathVariable int authorId) {
		List<Post> posts = new ArrayList<Post>();
		posts = service.getPostByAuthorId(authorId);
		return ResponseEntity.ok(posts);
	}

	@GetMapping("/byid{id}") // sets variable as part of the url
	public ResponseEntity<?> getPostById(@PathVariable int id) {
		// @Pathvariable sets the variable in the url to the parameter

		Post post = service.get(id);
		if (post == null) {
			return ResponseEntity.ok(0);
		} else {
			return ResponseEntity.ok(post);

		}
	}

	@GetMapping("/get/bytitle{title}") // sets variable as part of the url
	public void getPostByUsername(@PathVariable String title, HttpServletResponse response) throws IOException {
		// @Pathvariable sets the variable in the url to the parameter
		
		List<Post> post = service.getPostByTitle(title);
	
			ResponseEntity.ok(post);
		
		// TODO don't throw exception

		// TODO CRUD FRIENDS
		// TODO getpostbyname
		// TODO getPostsbyPostId
	}

//		@GetMapping("/getbypostid") //will add if we have time
//		public void getPostByPostId(@PathVariable int postId, HttpServletResponse response) throws IOException{
//				
//		}
//		@PostMapping
//		public void SavePost(@Valid @ModelAttribute Post post, BindingResult result, HttpServletResponse response) throws IOException { 
//			// assumption of a form of some kind
//			if (result.hasErrors()) {
//				response.getWriter().println("Inserted unsuccessfully");
//				
//			}
//			service.save(post);
//			response.getWriter().println("Inserted successfully");
//		}
	@PostMapping()
	public ResponseEntity<?> SavePost(@RequestBody Post post) {
		if (post.getId() == 0) {
			service.save(post);
			
			return ResponseEntity.ok().body("Post saved with Title = " + post.getTitle() + " id = " + post.getId());
		} else
			return ResponseEntity.ok(0); 
	}

	@PutMapping
	public ResponseEntity<?> UpdatePost(@RequestBody Post post) {
		// assumption of a form of some kind
		if (service.get(post.getId()).getId() == 0) { // TODO ask people if null is what get actually returns
			return ResponseEntity.ok("post is not currently in database. save post first");
		} else {
			service.update(post);
			return ResponseEntity.ok("Inserted successfully");
		}
	}

	@DeleteMapping
	public ResponseEntity<?> DeletePost(@RequestBody Post post) {
		// assumption of a form of some kind

		if (post.getId()==0) { // TODO ask people if null is what get actually returns
			return ResponseEntity.ok("post is not currently in database. create post first");
		} else {
			service.delete(post);
			return ResponseEntity.ok("Deleted successfully. It wasn't a good post anyway");
		}
	}

}

// TODO CRUD Posts
// TODO getPostsbyId
// TODO getpostsbyuser
