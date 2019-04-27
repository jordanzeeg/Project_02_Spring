package com.java.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.CommentDao;
import com.java.dto.Comment;
import com.java.util.LoggerSingleton;

@Service
public class CommentService implements CommentServiceInterface<Comment> {

	@Autowired CommentDao dao;
	
	@Override
	public Comment get(int id) {
		LoggerSingleton.getLogger().info("FETCHING Comment based commentId: "+id);
		Comment comment = dao.get(id);
		LoggerSingleton.getLogger().info("FETCHED comment based commentId: "+id);
		return comment;
	}

	@Override
	public List<Comment> getAll() {
		LoggerSingleton.getLogger().info("FETCHING all Comments objects");
		List<Comment> comment = dao.getAll();
		LoggerSingleton.getLogger().info("FETCHED all Comments objects");
		return comment;
	}

	@Override
	public void save(Comment t) {
		LoggerSingleton.getLogger().info("SAVING Comment object to database");
		dao.save(t);
		LoggerSingleton.getLogger().info("SAVED Comment object to database");
		
	}

	@Override
	public void update(Comment t) {
		LoggerSingleton.getLogger().info("UPDATING Comment object to database");
		dao.update(t);
		LoggerSingleton.getLogger().info("UPDATED Comment object to database");
		
	}

	@Override
	public void delete(Comment t) {
		LoggerSingleton.getLogger().info("DELETING Comment object from the database");
		dao.delete(t);
		LoggerSingleton.getLogger().info("DELETED Comment object from the database");
		
	}

	// Methods that don't access the db directly
	
	@Override
	public List<Comment> getCommentByPostId(int postId) {//get comment by postId(order by timestamp, oldest first)
		List<Comment> c_like = new ArrayList<>(); //creating a new list for comment that has postId as the postId passed in
		LoggerSingleton.getLogger().info("GETTING Comment based on postId: "+postId);
		for(int i=0; i<getAll().size(); i++) {//going through the list
			if(getAll().get(i).getPost().getId() == postId) {
				LoggerSingleton.getLogger().info("ADDING Comment based on postId: "+postId);
				c_like.add(getAll().get(i));
				LoggerSingleton.getLogger().info("ADDED Comment based on postId: "+postId);
			}
		}
		return c_like; //return ALL the likes based on the commentId
		//whichever method that called this method will have to retrieve comments in the order of timestamp it wants
	}

	@Override
	public List<Comment> getCommentByAuthorName(String authorName) {//get all the comments written by this author
		List<Comment> comment = new ArrayList<>(); //creating a new list for comment that has postId as the postId passed in
		LoggerSingleton.getLogger().info("GETTING Comment based on authorName: "+ authorName);
		for(int i=0; i<getAll().size(); i++) {//going through the list
			if(getAll().get(i).getAuthor().getFirstName().equals(authorName)) {
				LoggerSingleton.getLogger().info("ADDING Comment based on authorName: "+authorName);
				comment.add(getAll().get(i));
				LoggerSingleton.getLogger().info("ADDED Comment based on authorName: "+authorName);
			}
		}
		LoggerSingleton.getLogger().info("RETURNING list of Comments based on authorName: "+authorName);
		return comment; //return ALL the likes based on the commentId
		//whichever method that called this method will have to retrieve comments in the order of timestamp it wants
	}	
}
