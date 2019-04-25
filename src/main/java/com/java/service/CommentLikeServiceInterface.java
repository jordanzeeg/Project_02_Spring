package com.java.service;

import java.util.List;

import com.java.dto.CommentLike;

public interface CommentLikeServiceInterface<T> {
	public CommentLike get(int id);//get a comment like based on its own id

	public List<CommentLike> getAll();//get ALL comment likes on the application

	public void save(CommentLike t);//save 1 comment like object

	public void update(CommentLike t);//updating 1 comment like object

	public void delete(CommentLike t);//deleting 1 comment like object
	
	//get List of CommentLike based on CommentId


	public List<CommentLike> getCommentLikeBasedOnCommentId(int commentId);
	

}
