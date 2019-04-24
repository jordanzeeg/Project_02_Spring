package com.java.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.CommentLikeDao;
import com.java.dto.Comment;
import com.java.dto.CommentLike;
import com.java.util.LoggerSingleton;

@Service
public class CommentLikeService implements CommentLikeServiceInterface<Comment> {
	
	@Autowired CommentLikeDao dao;
	
	@Override
	public CommentLike get(int id) {
		LoggerSingleton.getLogger().info("FETCHING CommentLike object based on CommenLikeId: "+id);
		CommentLike clike = dao.get(id);
		LoggerSingleton.getLogger().info("FETCHED CommentLike object based on CommentLikeId: "+id);
		return clike;
	}

	@Override
	public List<CommentLike> getAll() {
		LoggerSingleton.getLogger().info("FETCHING all CommentLike objects");
		List<CommentLike> c_list = dao.getAll();
		LoggerSingleton.getLogger().info("FETCHED all CommentLike objects");
		return c_list;
	}

	@Override
	public void save(CommentLike t) {
		LoggerSingleton.getLogger().info("SAVING CommentLike object database");
		dao.save(t);
		LoggerSingleton.getLogger().info("SAVED CommentLike object to database");
	}

	@Override
	public void update(CommentLike t) {
		LoggerSingleton.getLogger().info("UPDATING CommentLike object database");
		dao.update(t);
		LoggerSingleton.getLogger().info("UPDATED CommentLike object to database");
		
	}

	@Override
	public void delete(CommentLike t) {
		LoggerSingleton.getLogger().info("DELETING CommentLike object from the database");
		dao.delete(t);
		LoggerSingleton.getLogger().info("DELETED CommentLike object from the database");
		
	}

	// Methods that don't access the db directly
	
	@Override
	public List<CommentLike> getLikeBasedOnCommentId(int commentId) {
		List<CommentLike> c_like = new ArrayList<>(); //creating a new list for likes that has commentId as the commentId passed in
		LoggerSingleton.getLogger().info("Getting likes based on CommentId: "+commentId);
		for(int i=0; i<getAll().size(); i++) {//going through the list
			if(getAll().get(i).getComment().getId() == commentId) {
				LoggerSingleton.getLogger().info("ADDING likes to new list with CommentId: "+commentId);
				c_like.add(getAll().get(i));
				LoggerSingleton.getLogger().info("ADDED likes based on CommentId: "+commentId);
			}
		}
		LoggerSingleton.getLogger().info("RETURNING list of CommentLike objects based on commentId: "+commentId);
		return c_like; //return ALL the likes based on the commentId
	}

	

	
}
