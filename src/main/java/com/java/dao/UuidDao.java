package com.java.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.java.dto.Friend;
import com.java.dto.PostLike;
import com.java.dto.Uuidclass;
import com.java.util.LoggerSingleton;

@Repository
public class UuidDao implements Dao<Uuidclass> {
	
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sf;
	
	
	//doesn't work
	public Uuidclass get(Friend friend) {
		Uuidclass uuid = new Uuidclass();
		String username = friend.getUsername();
		Session s = sf.openSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		s.beginTransaction(); // original

		CriteriaQuery<Uuidclass> q = cb.createQuery(Uuidclass.class);
		Root<Uuidclass> c = q.from(Uuidclass.class);
		ParameterExpression<String> p = cb.parameter(String.class);
		q.select(c).where(cb.equal(c.get("username"), p));
		TypedQuery<Uuidclass> query = s.createQuery(q);
		query.setParameter(p, username);
		try {
			uuid = query.getSingleResult();
		} catch (NoResultException e) {
			LoggerSingleton.getLogger().info("Empty list created in FriendDao.getByUsername()");
		}
		s.getTransaction().commit();

		s.close();

		return uuid;
	}
	

	@Override
	public List<Uuidclass> getAll() {
		Session session=sf.openSession();
		
		//Use nondeprecated things to do criteria
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Uuidclass> criteriaQuery = builder.createQuery(Uuidclass.class);
		criteriaQuery.from(Uuidclass.class);
		List<Uuidclass> list = session.createQuery(criteriaQuery).getResultList(); //call session 
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

	@Override
	public Uuidclass get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
