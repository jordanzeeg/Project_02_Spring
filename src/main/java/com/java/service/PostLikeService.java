package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.java.dao.PostLikeDao;
import com.java.dto.CommentLike;
import com.java.dto.PostLike;

public class PostLikeService implements PostLikeServiceInterface<PostLike> {
	
	@Autowired PostLikeDao dao;
	
	@Override
	public CommentLike get(int id) {
		return dao.get(id);
	}

	@Override
	public List<CommentLike> getAll() {
		return dao.getAll();
	}

	@Override
	public void save(CommentLike t) {
		dao.save(t);
	}

	@Override
	public void update(CommentLike t) {
		dao.update(t);
	}

	@Override
	public void delete(CommentLike t) {
		dao.delete(t);	
	}
}
