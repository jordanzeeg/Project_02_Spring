package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.FriendDao;
import com.java.dto.Friend;

@Service
public class FriendService implements FriendServiceInterface<Friend> {

	@Autowired FriendDao dao;
	
	@Override
	public Friend get(int id) {
		return dao.get(id);
	}

	@Override
	public List<Friend> getAll() {
		return dao.getAll();
	}

	@Override
	public void save(Friend t) {
		dao.save(t);
		
	}

	@Override
	public void update(Friend t) {
		dao.update(t);
	}

	@Override
	public void delete(Friend t) {
		dao.delete(t);		
	}
}
