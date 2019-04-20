package com.java.service;

import java.util.List;

import com.java.dto.CommentLike;

public interface PostLikeServiceInterface<T> {
	public CommentLike get(int id);
	
	public List<CommentLike> getAll();
	
	public void save(CommentLike t);
	
	public void update(CommentLike t);
	
	public void delete(CommentLike t);
	
	//get postlike by post id
}
