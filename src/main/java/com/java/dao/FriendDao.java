package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
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
	public Friend getByUsername(String username) {
		Friend friend = new Friend();
		Session s=sf.openSession();
		  CriteriaBuilder cb = s.getCriteriaBuilder();
		  s.beginTransaction();
		  CriteriaQuery<Friend> q = cb.createQuery(Friend.class);
		  Root<Friend> c = q.from(Friend.class);
		  ParameterExpression<String> p = cb.parameter(String.class);
		   q.select(c).where(cb.equal(c.get("username"), p));
		   TypedQuery<Friend> query = s.createQuery(q);
		   query.setParameter(p, username);
		   try {
		   friend = query.getSingleResult();
		   }
		   catch(NoResultException e) {
			   LoggerSingleton.getLogger().info("Empty list created in FriendDao.getByUsername()" );
		   }
		   s.getTransaction().commit();
		s.close();

		return friend;
	}
	

	@Override
	public List<Friend> getAll() { //create the list using criteriaBuilder
		List<Friend> list = new ArrayList<Friend>();
		Session session=sf.openSession();
		session.beginTransaction();
		//Use nondeprecated things to do criteria
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Friend> criteriaQuery = builder.createQuery(Friend.class);
		criteriaQuery.from(Friend.class);
		try
		{
		list = session.createQuery(criteriaQuery).getResultList(); //call session 
		}
		catch(NoResultException e){
			LoggerSingleton.getLogger().info("Empty list created in FriendDao.getAll()" );	
		}
		session.getTransaction().commit();
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
