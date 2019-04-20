package com.java.service;

import java.util.List;

import com.java.dto.Post;

public interface PostServiceInterface<T> {
	public Post get(int id);
	
	public List<Post> getAll();
	
	public void save(Post t);
	
	public void update(Post t);
	
	public void delete(Post t);
	
	//get post by author (order by timestamp newest first)

}
