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


import com.java.dto.Post;
import com.java.service.FriendService;
import com.java.service.PostService;

@Controller
@RequestMapping("/posts.do")
public class PostController {
	@Autowired 
	PostService service;
	
	//TODO don't throw exception
	@GetMapping
	public void getPosts(HttpServletResponse response) throws IOException {
		
		List<Post> posts = service.getAll();
		response.getWriter().println(posts);
	}

		//TODO don't throw exception
	@GetMapping("/getpostsbyuser{id}") //pass in user id
	public void GetPostsByUserId(@PathVariable int authorId, HttpServletResponse response) throws IOException {
		List<Post> posts = service.getPostByAuthorId(authorId);
		response.getWriter().println(posts);
	}
	
		@GetMapping("/get/byid{id}") // sets variable as part of the url
		public void getPostById(@PathVariable int id, HttpServletResponse response) throws IOException { 
			// @Pathvariable sets the variable in the url to the parameter

			Post post = service.get(id);
			if (post == null) {
				response.getWriter().println("Post object not found");
			} else {
				try {
					response.getWriter().println(post);
				} catch (IOException e) {
					response.getWriter().println("id not found");
					e.printStackTrace();
				}
			}
			}
		@GetMapping("/get/bytitle{title}") // sets variable as part of the url
		public void getPostByUsername(@PathVariable String title, HttpServletResponse response) throws IOException { 
			// @Pathvariable sets the variable in the url to the parameter

			Post post = (Post) service.getPostByTitle(title);
			if (post == null) {
				response.getWriter().println("Post object not found");
			} else {
				try {
					response.getWriter().println(post);
				} catch (IOException e) {
					response.getWriter().println("id not found");
					e.printStackTrace();
				}
			}
			// TODO don't throw exception

			// TODO CRUD FRIENDS
			// TODO getpostbyname
			// TODO getPostsbyPostId
		}
//		@GetMapping("/getbypostid") //will add if we have time
//		public void getPostByPostId(@PathVariable int postId, HttpServletResponse response) throws IOException{
//				
//		}
		@PostMapping("/save")
		public void SavePost(@Valid @ModelAttribute Post post, BindingResult result, HttpServletResponse response) throws IOException { 
			// assumption of a form of some kind
			if (result.hasErrors()) {
				response.getWriter().println("Inserted unsuccessfully");
			}
			service.save(post);
			response.getWriter().println("Inserted successfully");
		}

		
		@PostMapping("/update")
		public void UpdatePost(@Valid @ModelAttribute Post post, BindingResult result, HttpServletResponse response) throws IOException {
			// assumption of a form of some kind
			if (result.hasErrors()) {
				response.getWriter().println("Inserted unsuccessfully");
			}
			else if(service.get(post.getId())== null) { //TODO ask people if null is what get actually returns
				response.getWriter().println("post is not currently in database. save post first");
			}else
			{
			service.update(post);
			response.getWriter().println("Inserted successfully");
			}
		}
		@PostMapping("/delete")
		public void DeletePost(@Valid @ModelAttribute Post post, BindingResult result, HttpServletResponse response) throws IOException { 
			// assumption of a form of some kind
			if (result.hasErrors()) {
				response.getWriter().println("Deleted unsuccessfully");
			}
			else if(service.get(post.getId())== null) { //TODO ask people if null is what get actually returns
				response.getWriter().println("post is not currently in database. create post first");
			}else
			{
			service.delete(post);
			response.getWriter().println("Deleted successfully. It wasn't a good post anyway");
			}
		}
		

}



		//TODO CRUD Posts
		//TODO getPostsbyId
		//TODO getpostsbyuser
		
		

