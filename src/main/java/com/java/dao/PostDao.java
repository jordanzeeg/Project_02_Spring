package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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

		List<Post> list = new ArrayList<Post>();

		Query<Post> q = session.createQuery("from Post"); //hql query select a.firstName, a.lastName from Book b join b
		// .authors a
		// where b.id = :id
		list = q.list();								//join Post.friend
		session.close();
		return list;
	}
//	@Override
//	public List<Post> getAll() {
//		List<Post> list = new ArrayList<Post>();
//		Session session=sf.openSession();
//		Query<Post> q = session.createQuery("from Post"); //hql query select a.firstName, a.lastName from Book b join b.authors a where b.id = :id
//		list = q.list();								//join Post.friend
//		session.close();
//		return list;
//	}
//	

	@Override
	public void save(Post t) {
		Session s =sf.openSession();
		s.beginTransaction();
		s.save(t);
		s.getTransaction().commit();
		s.close();
	}

	@Override
	public void update(Post t) {
		Session s =sf.openSession();
		s.beginTransaction();
		s.update(t);
		s.getTransaction().commit();
		s.close();
	}

	@Override
	public void delete(Post t) {
		Session s = sf.openSession();
		s.beginTransaction();
		s.delete(t);
		s.getTransaction().commit();
		s.close();		
	}
	

}
