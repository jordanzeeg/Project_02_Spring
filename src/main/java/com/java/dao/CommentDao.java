package com.java.dao;

import java.util.List;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.java.dto.Comment;

@Repository
public class CommentDao implements Dao<Comment> {
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sf;
	
	@Override
	public Comment get(int id) {
		Session s=sf.openSession();
		Comment t = s.get(Comment.class, id);
		s.close();
		return t;
	}

	@Override
	public List<Comment> getAll() {
		Session session=sf.openSession();
		
		//Use nondeprecated things to do criteria
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Comment> criteriaQuery = builder.createQuery(Comment.class);
		criteriaQuery.from(Comment.class);
		List<Comment> list = session.createQuery(criteriaQuery).getResultList(); //call session 
		session.close();
		return list;
		
	}

	@Override
	public void save(Comment t) {
		Session s =sf.openSession();
		s.beginTransaction();
		s.save(t);
		s.getTransaction().commit();
		s.close();
		
	}

	@Override
	public void update(Comment t) {
		Session s = sf.openSession();
		s.beginTransaction();
		s.update(t);
		s.getTransaction().commit();
		s.close();
		
	}

	@Override
	public void delete(Comment t) {
		Session s=sf.openSession();
		s.beginTransaction();
		s.delete(t);
		s.getTransaction().commit();
		s.close();
		
	}


}
