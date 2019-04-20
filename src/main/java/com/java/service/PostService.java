package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.PostDao;
import com.java.dto.Post;

@Service
public class PostService implements PostServiceInterface<Post> {
	@Autowired PostDao dao;
	
	@Override
	public Post get(int id) {
		return dao.get(id);
	}

	@Override
	public List<Post> getAll() {
		return dao.getAll();
	}

	@Override
	public void save(Post t) {
		dao.save(t);
	}

	@Override
	public void update(Post t) {
		dao.update(t);	
	}

	@Override
	public void delete(Post t) {
		dao.delete(t);
	}
}
