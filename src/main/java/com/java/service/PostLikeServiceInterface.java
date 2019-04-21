package com.java.service;

import java.util.List;

import com.java.dto.PostLike;

public interface PostLikeServiceInterface<T> {
	
	public PostLike get(int id);//get all post likes based on post id
	
	public List<PostLike> getAll();//get all the post likes
	
	public void save(PostLike t);//save a post like
	
	public void update(PostLike t);//update 
	
	public void delete(PostLike t);
	
	//get PosttLikes based on PostId
	public List<PostLike> getLikeBasedOnPosttId(int postId);
}
