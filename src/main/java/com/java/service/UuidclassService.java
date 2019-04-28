package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.PostDao;
import com.java.dao.UuidDao;
import com.java.dto.Friend;
import com.java.dto.Post;
import com.java.dto.Uuidclass;
import com.java.util.LoggerSingleton;


@Service
public class UuidclassService {
	@Autowired
	UuidDao dao;
	
	
	public Uuidclass get(Friend friend) {// return post based on postId
		LoggerSingleton.getLogger().info("FETCHING Post objects from the database based on PostId: " + friend.getId());
		Uuidclass uuid = dao.get(friend);
		LoggerSingleton.getLogger().info("FETCHED Post objects from the database based on PostId: " + friend.getId());
		return uuid;
	}

	
	public List<Uuidclass> getAll() {// return all posted on the application
		LoggerSingleton.getLogger().info("FETCHING all Post objects from the database");
		List<Uuidclass> post = dao.getAll();
		LoggerSingleton.getLogger().info("FETCHED all Post objects from the database");
		return post;
	}

	
	public void save(Uuidclass t) {// save a Post object to the db
		LoggerSingleton.getLogger().info("SAVING Post object to the database");
		dao.save(t);
		LoggerSingleton.getLogger().info("SAVED Post object to the database");
	}

	
	public void update(Uuidclass t) {// update a Post object to the db
		LoggerSingleton.getLogger().info("UPDATING Post object to the database");
		dao.update(t);
		LoggerSingleton.getLogger().info("UPDATED Post object to the database");
	}

	
	public void delete(Uuidclass t) {// delete a Post object from the db
		LoggerSingleton.getLogger().info("DELETING Post object from the database");
		dao.delete(t);
		LoggerSingleton.getLogger().info("DELETED Post object from the database");
	}
}
