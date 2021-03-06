package com.java.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.PostDao;
import com.java.dto.Friend;
import com.java.dto.Post;
import com.java.util.LoggerSingleton;

@Service
public class PostService implements PostServiceInterface<Post> {
	@Autowired
	PostDao dao;

	@Override
	public Post get(int id) {// return post based on postId
		LoggerSingleton.getLogger().info("FETCHING Post objects from the database based on PostId: " + id);
		Post post = dao.get(id);
		LoggerSingleton.getLogger().info("FETCHED Post objects from the database based on PostId: " + id);
		return post;
	}

	@Override
	public List<Post> getAll() {// return all posted on the application
		LoggerSingleton.getLogger().info("FETCHING all Post objects from the database");
		List<Post> post = dao.getAll();
		LoggerSingleton.getLogger().info("FETCHED all Post objects from the database");
		return post;
	}

	@Override
	public void save(Post t) {// save a Post object to the db
		LoggerSingleton.getLogger().info("SAVING Post object to the database");
		dao.save(t);
		LoggerSingleton.getLogger().info("SAVED Post object to the database");
	}

	@Override
	public void update(Post t) {// update a Post object to the db
		LoggerSingleton.getLogger().info("UPDATING Post object to the database");
		dao.update(t);
		LoggerSingleton.getLogger().info("UPDATED Post object to the database");
	}

	@Override
	public void delete(Post t) {// delete a Post object from the db
		LoggerSingleton.getLogger().info("DELETING Post object from the database");
		dao.delete(t);
		LoggerSingleton.getLogger().info("DELETED Post object from the database");
	}

	@Override
	public List<Post> getPostByAuthorId(int authorId) {// get post based on authorId
		List<Post> post = getAll(); // creating a new list for post that has authorId as the authorId passed in
		List<Friend> friendsByPost = new ArrayList<>();
		List<Post> returnPosts = new ArrayList<>();
		LoggerSingleton.getLogger().info("GETTING Post based on authorId: " + authorId);
		for (int i = 0; i < post.size(); i++) {// going through the list
			friendsByPost = post.get(i).getFriends();
			for (int j = 0; j < friendsByPost.size(); j++) {
				if (friendsByPost.get(j).getId() == authorId) {
					LoggerSingleton.getLogger().info("ADDING Post based on authorId: " + authorId);
					returnPosts.add(post.get(i));
					LoggerSingleton.getLogger().info("ADDED Post based on authorId: " + authorId);
				}
			}
		}
		LoggerSingleton.getLogger().info("RETURNING list of Post based on authorId" + authorId);
		return returnPosts; // return ALL the post based on the authortId
		// whichever method called this method will have to retrieve based on timestamp
	}

	/* This will done in the front end?? */

	@Override
	public List<Post> getPostByTitle(String title) { // TODO ask poho why this returns a list and not a single one
		List<Post> post = new ArrayList<>(); // creating a new list for post that has authorId as the authorId passed in
		LoggerSingleton.getLogger().info("GETTING Post based on title: " + title);
		for (int i = 0; i < getAll().size(); i++) {// going through the list
			if (getAll().get(i).getTitle() == title) {
				LoggerSingleton.getLogger().info("ADDING Post to list based on title: " + title);
				post.add(getAll().get(i));
				LoggerSingleton.getLogger().info("ADDED Post to list based on title: " + title);
			}
		}
		LoggerSingleton.getLogger().info("RETURNING list of Post based on title" + title);
		return post; // return ALL the post based on the title
	}
}
