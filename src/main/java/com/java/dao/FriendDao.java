package com.java.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.java.dto.Friend;
import com.java.util.LoggerSingleton;

@Repository
public class FriendDao implements Dao<Friend> {
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sf;
	
	@Override
	public Friend get(int id) {
		Session s=sf.openSession();
		Friend t = s.get(Friend.class, id);
		s.close();
		return t;
	}
	

	@Override
	public List<Friend> getAll() { //create the list using criteriaBuilder
		Session session=sf.openSession();
		
		//Use nondeprecated things to do criteria
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Friend> criteriaQuery = builder.createQuery(Friend.class);
		criteriaQuery.from(Friend.class);
		List<Friend> list = session.createQuery(criteriaQuery).getResultList(); //call session 
		session.close();
		return list;
	}
	
	
	//insert friend into the database if friend does not exist
	@Override
	public void save(Friend t) {
		LoggerSingleton.getLogger().info("In the save method");
		Session s =sf.openSession();
		s.beginTransaction();
		s.save(t);
		s.getTransaction().commit();
		s.close();
	}

	
	//update the information for a friend that already exists
	@Override
	public void update(Friend t) {
		Session s = sf.openSession();
		s.beginTransaction();
		s.update(t);
		s.getTransaction().commit();
		s.close();		
		
	}

	
	//delete object for a friend that currently exists
	@Override
	public void delete(Friend t) {
		Session s=sf.openSession();
		s.beginTransaction();
		s.delete(t);
		s.getTransaction().commit();
		s.close();
		
	}

}
