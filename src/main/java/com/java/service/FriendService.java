package com.java.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.FriendDao;
import com.java.dto.Friend;
import com.java.dto.Post;
import com.java.util.LoggerSingleton;

@Service
public class FriendService implements FriendServiceInterface<Friend> {

	@Autowired FriendDao dao;
	
	@Override
	public Friend get(int id) {
		LoggerSingleton.getLogger().info("FETCHING Friend object based on friendId: "+id);
		Friend friend = dao.get(id);
		LoggerSingleton.getLogger().info("FETCHED Friend object based on friendId: "+id);
		return friend;
	}

	@Override
	public List<Friend> getAll() {
		LoggerSingleton.getLogger().info("FETCHING ALL Friend objects");
		List<Friend> friend = dao.getAll();
		LoggerSingleton.getLogger().info("FETCHED ALL Friend objects");
		return friend;
	}

	@Override
	public void save(Friend t) {
		LoggerSingleton.getLogger().info("SAVING a Friend object to database");
		dao.save(t);
		LoggerSingleton.getLogger().info("SAVED a Friend object to database");
		
	}

	@Override
	public void update(Friend t) {
		LoggerSingleton.getLogger().info("UPDATING a Friend object to database");
		dao.update(t);
		LoggerSingleton.getLogger().info("UPDATED a Friend object to database");
	}

	@Override
	public void delete(Friend t) {
		LoggerSingleton.getLogger().info("DELETING a Friend object from database");
		dao.delete(t);	
		LoggerSingleton.getLogger().info("DELETED a Friend object to database");
	}
	
	public Friend getByUsername(String username) {
		LoggerSingleton.getLogger().info("FETCHING Friend object based on friendUsername: "+ username);
		Friend friend = dao.getByUsername(username);
		LoggerSingleton.getLogger().info("FETCHED Friend object based on friendUsername: "+ username);
		LoggerSingleton.getLogger().info("FETCHED Friend object based on friendId: "+ friend.getId());
		return friend;
	}

//	public Friend getByUsername(String username) {
//		LoggerSingleton.getLogger().info("FETCHING Friend object based on username: "+username);
//		List<Friend> friends = getAll();
//		Friend friend = new Friend();
//		for(int i = 0; i< friends.size();i++) {
//			if(friends.get(i).getUsername() == username) {
//				friend = storeFriend(friends.get(i));
//				LoggerSingleton.getLogger().info("Found friend in List by Username "+username);
//			}
//		}	
//		LoggerSingleton.getLogger().info("FETCHED Friend object based on username: "+username);
//		return friend;
//	}
	
	 public Friend storeFriend(Friend friend) {
		 Friend friend2 = new Friend();
			friend2.setId(friend.getId());
			friend2.setUsername(friend.getUsername());
			friend2.setFirstName(friend.getFirstName());
			friend2.setLastName(friend.getLastName());
			friend2.setPassword(friend.getPassword());
			friend2.setPosts(friend.getPosts());
			friend2.setEmail(friend.getEmail());
			return friend2;
	 }
}
