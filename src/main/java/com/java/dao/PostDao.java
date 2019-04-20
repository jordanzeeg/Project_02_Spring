package com.java.dao;

import java.util.List;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.java.dto.Post;

@Repository
public class PostDao implements Dao<Post> {
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sf;
	
	@Override
	public Post get(int id) {
		Session s=sf.openSession();
		Post t = s.get(Post.class, id);
		s.close();
		return t;
	}

	@Override
	public List<Post> getAll() {
		Session session=sf.openSession();
		
		//Use nondeprecated things to do criteria
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Post> criteriaQuery = builder.createQuery(Post.class);
		criteriaQuery.from(Post.class);
		List<Post> list = session.createQuery(criteriaQuery).getResultList(); //call session 
		session.close();
		return list;
	}

	@Override
	public void save(Post t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Post t) {
		Session s =sf.openSession();
		s.beginTransaction();
		s.save(t);
		s.getTransaction().commit();
		s.close();
	}

	@Override
	public void delete(Post t) {
		Session s = sf.openSession();
		s.beginTransaction();
		s.update(t);
		s.getTransaction().commit();
		s.close();		
	}

}
