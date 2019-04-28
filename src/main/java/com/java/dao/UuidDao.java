package com.java.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.java.dto.PostLike;
import com.java.dto.Uuidclass;

public class UuidDao implements Dao<Uuidclass> {
	
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sf;
	
	
	@Override
	public Uuidclass get(int id) {
		Session s=sf.openSession();
		Uuidclass t = s.get(Uuidclass.class, id);
		s.close();
		return t;
	
	}

	@Override
	public List<Uuidclass> getAll() {
		Session session=sf.openSession();
		
		//Use nondeprecated things to do criteria
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<PostLike> criteriaQuery = builder.createQuery(PostLike.class);
		criteriaQuery.from(PostLike.class);
		List<PostLike> list = session.createQuery(criteriaQuery).getResultList(); //call session 
		session.close();
		return null;
	}

	@Override
	public void save(Uuidclass t) {
		Session s =sf.openSession();
		s.beginTransaction();
		s.save(t);
		s.getTransaction().commit();
		s.close();
		
	}

	@Override
	public void update(Uuidclass t) {
		Session s = sf.openSession();
		s.beginTransaction();
		s.update(t);
		s.getTransaction().commit();
		s.close();	
		
	}

	@Override
	public void delete(Uuidclass t) {
		Session s=sf.openSession();
		s.beginTransaction();
		s.delete(t);
		s.getTransaction().commit();
		s.close();
		
	}

}
