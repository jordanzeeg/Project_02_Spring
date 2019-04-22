package com.java.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.java.dto.Friend;
import com.java.util.LoggerSingleton;

public interface FriendServiceInterface<T> {
	
	public Friend get(int id); //Get a Friend/user object based on its id
	

	public List<Friend> getAll();//Get ALL the Friends/users from the db
	
	
	//insert a Friend/user object into the database if friend does not exist
	public void save(Friend t);

	
	//update the information for a Friend/user object that already exists
	public void update(Friend t);

	
	//delete a Friend/user object that currently exists
	public void delete(Friend t);
	
	//login validation
	public boolean passwordValidation(String password);
	
	//username validation
	public boolean usernameValidation(String username);
	
	//email validation
	public boolean emailValidation(String email);
	
}
