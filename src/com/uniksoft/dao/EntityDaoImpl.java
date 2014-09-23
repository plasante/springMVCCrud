package com.uniksoft.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EntityDaoImpl<T> implements EntityDao<T> {

	@Autowired 
	private SessionFactory sessionFactory;	// sessionFactory is created in springMVCCrud-servlet.xml
	
	@Override
	public void addEntity(T entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public void updateEntity(T entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listEntities(Class<T> entity) {
		String className = entity.getSimpleName();
		return sessionFactory.getCurrentSession().createQuery("from " + className).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getEntityById(Class<T> entity, Integer id) {
		String className = entity.getSimpleName();
		Session session = sessionFactory.getCurrentSession();
		List<T> list = session.createQuery("from " + className + " b where b.id = :entityId").setParameter("entityId", id).list();
		return list.size() > 0 ? (T)list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeEntity(Class<T> entity, Integer id) {
		T ent = (T)sessionFactory.getCurrentSession().load(entity, id);
		if(null != ent) {
			sessionFactory.getCurrentSession().delete(ent);
		}
	}

}
