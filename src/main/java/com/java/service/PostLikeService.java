package com.java.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.PostLikeDao;
import com.java.dto.PostLike;
import com.java.util.LoggerSingleton;

@Service
public class PostLikeService implements PostLikeServiceInterface<PostLike> {
	
	@Autowired PostLikeDao dao;
	
	@Override
	public PostLike get(int id) {
		LoggerSingleton.getLogger().info("FETCHING PostLike based on postLikeId: "+id);
		PostLike postlike = dao.get(id);
		LoggerSingleton.getLogger().info("FETCHED PostLike based on postLikeId: "+id);
		return postlike;
	}

	@Override
	public List<PostLike> getAll() {
		LoggerSingleton.getLogger().info("FETCHING all PostLike objects");
		List<PostLike> postlike = dao.getAll();
		LoggerSingleton.getLogger().info("FETCHED all PostLike objects");
		return postlike;
	}

	@Override
	public void save(PostLike t) {
		LoggerSingleton.getLogger().info("SAVING a PostLike object to the database");
		dao.save(t);
		LoggerSingleton.getLogger().info("SAVED a PostLike object to the database");
	}

	@Override
	public void update(PostLike t) {
		LoggerSingleton.getLogger().info("UPDATING a PostLike object to the database");
		dao.update(t);
		LoggerSingleton.getLogger().info("UPDATED a PostLike object to the database");
	}

	@Override
	public void delete(int postid, int authorid) {
		LoggerSingleton.getLogger().info("DELETING a PostLike object from the database");
		dao.deleteByPostAndAuthor(postid, authorid);	
		LoggerSingleton.getLogger().info("DELETED a PostLike object to the database");
	}

	//Method not hitting the database directly
	
	@Override
	public List<PostLike> getLikeBasedOnPosttId(int postId) {
		List<PostLike> c_like = new ArrayList<>(); //creating a new list for likes that has postId as the postId passed in
		LoggerSingleton.getLogger().info("GETTING PostLike based on postId: "+postId);
		for(int i=0; i<getAll().size(); i++) {//going through the list
			if(getAll().get(i).getPost().getId() == postId) {
				LoggerSingleton.getLogger().info("ADDING PostLike based on postId: "+postId);
				c_like.add(getAll().get(i));
				LoggerSingleton.getLogger().info("ADDED PostLike based on postId: "+postId);
			}
		}
		LoggerSingleton.getLogger().info("RETURNING PostLike list based on postId: "+postId);
		return c_like; //return ALL the likes based on the posttId
		//methods calling this metho will only need the size
	}

	
}
