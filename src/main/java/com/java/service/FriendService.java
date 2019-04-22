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
		LoggerSingleton.getLogger().info("FETCHING Friend object based on username: "+username);
		List<Friend> friends = getAll();
		Friend friend =null;
		for(int i = 0; i< friends.size();i++) {
			if(friends.get(i).getUsername() == username) {
				friend = friends.get(i);
				LoggerSingleton.getLogger().info("Found friend in List by Username "+username);
			}else {
				LoggerSingleton.getLogger().info("Did not find friend in List by Username "+username);
			}
		}
		LoggerSingleton.getLogger().info("FETCHED Friend object based on username: "+username);
		return friend;
	}

	@Override
	public boolean usernameValidation(String username) {
		List<Friend> friends = getAll();
		for(int i = 0; i< friends.size();i++) {
			if(username.equals(friends.get(i).getUsername())) {//if user name exist or match any on the db 
				return true; //then return true
			}
		}
		return false; //else return false, no username exist
	}
	
	@Override
	public boolean emailValidation(String email) {
		List<Friend> friends = getAll();
		for(int i = 0; i< friends.size();i++) {
			if(email.equals(friends.get(i).getEmail())) {//if email exist or match any on the db
				return true; //then return true
			}
		}
		return false; // else return true, no email exist 
	}

	@Override
	public boolean passwordValidation(String password) {
		List<Friend> friends = getAll();
		for(int i = 0; i< friends.size();i++) {
			if(password.equals(friends.get(i).getPassword())) {//checking that username exist
				//if(password.equals(friends.get(i).getPassword())) {//then check that password match
					return true; //return true 
				}	
			}
		return false;
	}
}
