package com.java.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.java.dto.Comment;

public interface CommentServiceInterface<T> {
	
	public Comment get(int id);//getting a comment object based on its Id
	
	public List<Comment> getAll();//getting ALL the comments in the application
	
	public void save(Comment t);// saving a comment object to the db
	
	public void update(Comment t);// updating a comment object to the db
	
	public void delete(Comment t); // deleting a comment object from the db
	
	//get comment by postId(order by timestamp, oldest first)
	public List<Comment> getCommentByPostId(int postId);
	
	//get comment by authorName // MIGHT NOT NEEDED?? Since we CAN get the info directly from the Comment class itself
	public List<Comment> getCommentByAuthorName(String name);
	
}
