package com.java.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.java.dto.Comment;

public interface CommentServiceInterface<T> {
	
	public Comment get(int id);
	
	public List<Comment> getAll();
	
	public void save(Comment t);
	
	public void update(Comment t);
	
	public void delete(Comment t);
	
	//get comment by postid(order by timestamp, oldest first)
	
}
