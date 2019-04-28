package com.java.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.java.dto.PostLike;
import com.java.util.LoggerSingleton;

@Repository
public class PostLikeDao implements Dao<PostLike> {
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sf;

	@Override
	public PostLike get(int id) {
		Session s=sf.openSession();
		PostLike t = s.get(PostLike.class, id);
		s.close();
		return t;
	}

	@Override
	public List<PostLike> getAll() {
		Session session=sf.openSession();
		
		//Use nondeprecated things to do criteria
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<PostLike> criteriaQuery = builder.createQuery(PostLike.class);
		criteriaQuery.from(PostLike.class);
		List<PostLike> list = session.createQuery(criteriaQuery).getResultList(); //call session 
		session.close();
		return list;
	}

	@Override
	public void save(PostLike t) {
		Session s =sf.openSession();
		s.beginTransaction();
		s.save(t);
		s.getTransaction().commit();
		s.close();
	}

	@Override
	public void update(PostLike t) {
		Session s = sf.openSession();
		s.beginTransaction();
		s.update(t);
		s.getTransaction().commit();
		s.close();		
		
	}

	@Override
	public void delete(PostLike t) {
		Session s=sf.openSession();
		s.beginTransaction();
		s.delete(t);
		s.getTransaction().commit();
		s.close();
		
		
	}
	public void deleteByPostAndAuthor(int postid, int authorid) {
		try {
		PostLike postLike = new PostLike();
		Session session = sf.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<PostLike> criteriaQuery = builder.createQuery(PostLike.class);
		criteriaQuery.from(PostLike.class);
		
		List<PostLike> list = session.createQuery(criteriaQuery).getResultList(); //call session 
		

		session.close();
		
			
			for(int i = 0; i<list.size(); i++) {
				if(list.get(i).getAuthor().getId() == authorid)
				{
					if(list.get(i).getPost().getId() == postid) {
					Session session2 = sf.openSession();
					session2.beginTransaction();
					session2.delete(list.get(i));
					session2.getTransaction().commit();
					session2.close();
					}
				}
			}
		} catch (NoResultException e) {
			LoggerSingleton.getLogger().info("Empty list created in FriendDao.getByUsername()");
		}

	}

}