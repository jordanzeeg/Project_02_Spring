package com.java.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.java.dto.Friend;
import com.java.util.LoggerSingleton;

public interface FriendServiceInterface<T> {
	public Friend get(int id);
	

	public List<Friend> getAll();
	
	
	//insert friend into the database if friend does not exist
	public void save(Friend t);

	
	//update the information for a friend that already exists
	public void update(Friend t);

	
	//delete object for a friend that currently exists
	public void delete(Friend t);
	
}
