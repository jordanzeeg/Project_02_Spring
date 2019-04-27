package com.java.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.java.dto.CommentLike;

@Repository
public class CommentLikeDao implements Dao<CommentLike> {
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sf;
	
	@Override
	public CommentLike get(int id) {
		Session s=sf.openSession();
		CommentLike t = s.get(CommentLike.class, id);
		s.close();
		return t;

	}

	@Override
	public List<CommentLike> getAll() {
		Session s=sf.openSession();
		//Use nondeprecated things to do criteria
		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaQuery<CommentLike> criteriaQuery = builder.createQuery(CommentLike.class);
		criteriaQuery.from(CommentLike.class);
		List<CommentLike> list = s.createQuery(criteriaQuery).getResultList(); //call session
		s.close();
		return list;

	}

	@Override
	public void save(CommentLike t) {
		Session s =sf.openSession();
		s.beginTransaction();
		s.save(t);
		s.getTransaction().commit();
		s.close();
	}

	@Override
	public void update(CommentLike t) {
		Session s = sf.openSession();
		s.beginTransaction();
		s.update(t);
		s.getTransaction().commit();
		s.close();		
		
	}

	@Override
	public void delete(CommentLike t) {
		Session s=sf.openSession();
		s.beginTransaction();
		s.delete(t);
		s.getTransaction().commit();
		s.close();
		
	}



}
