package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.java.dao.CommentLikeDao;
import com.java.dto.Comment;
import com.java.dto.CommentLike;
import com.java.util.LoggerSingleton;

public class CommentLikeService implements CommentLikeServiceInterface<Comment> {
	
	@Autowired CommentLikeDao dao;
	
	@Override
	public CommentLike get(int id) {
		LoggerSingleton.getLogger().info("FETCHING likes of comment by id"+id);
		CommentLike clike = dao.get(id);
		LoggerSingleton.getLogger().info("FETCHED likes of comment by id"+id);
		return clike;
	}

	@Override
	public List<CommentLike> getAll() {
		LoggerSingleton.getLogger().info("FETCHING all likes of comment");
		List<CommentLike> c_list = dao.getAll();
		LoggerSingleton.getLogger().info("FETCHED all likes of comment");
		return c_list;
	}

	@Override
	public void save(CommentLike t) {
		LoggerSingleton.getLogger().info("SAVING likes to comment");
		dao.save(t);
		LoggerSingleton.getLogger().info("SAVED likes to comment");
	}

	@Override
	public void update(CommentLike t) {
		LoggerSingleton.getLogger().info("UPDATING likes to comment");
		dao.update(t);
		LoggerSingleton.getLogger().info("UPDATED likes to comment");
		
	}

	@Override
	public void delete(CommentLike t) {
		LoggerSingleton.getLogger().info("DELETING likes of comment");
		dao.delete(t);
		LoggerSingleton.getLogger().info("DELETED likes of comment");
		
	}
}
