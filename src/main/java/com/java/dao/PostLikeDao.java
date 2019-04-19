package com.java.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.java.dto.CommentLike;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class PostLikeDao implements Dao<CommentLike> {
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
		Session session=sf.openSession();
		
		//Use nondeprecated things to do criteria
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<CommentLike> criteriaQuery = builder.createQuery(CommentLike.class);
		criteriaQuery.from(CommentLike.class);
		List<CommentLike> list = session.createQuery(criteriaQuery).getResultList(); //call session 
		session.close();
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