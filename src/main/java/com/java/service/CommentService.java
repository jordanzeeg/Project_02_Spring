package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.java.dao.CommentDao;
import com.java.dto.Comment;
import com.java.util.LoggerSingleton;

public class CommentService implements CommentServiceInterface<Comment> {

	@Autowired CommentDao dao;
	
	@Override
	public Comment get(int id) {
		LoggerSingleton.getLogger().info("FETCHING comment by comment id"+id);
		Comment comment = dao.get(id);
		LoggerSingleton.getLogger().info("FETCHING comment by comment id"+id);
		return comment;
	}

	@Override
	public List<Comment> getAll() {
		LoggerSingleton.getLogger().info("FETCHING all comments. Return as a list");
		List<Comment> comment = dao.getAll();
		LoggerSingleton.getLogger().info("FETCHED all comments. Return as a list");
		return comment;
	}

	@Override
	public void save(Comment t) {
		LoggerSingleton.getLogger().info("SAVING comment to db");
		dao.save(t);
		LoggerSingleton.getLogger().info("SAVED comment to db");
		
	}

	@Override
	public void update(Comment t) {
		LoggerSingleton.getLogger().info("UPDATING comment to db");
		dao.update(t);
		LoggerSingleton.getLogger().info("UPDATED comment to db");
		
	}

	@Override
	public void delete(Comment t) {
		LoggerSingleton.getLogger().info("DELETING comment");
		dao.delete(t);
		LoggerSingleton.getLogger().info("DELETED comment");
		
	}	
}
