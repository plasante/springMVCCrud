package com.uniksoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniksoft.dao.EntityDao;

@Service
public class EntityServiceImpl<T> {

	@Autowired
	private EntityDao<T> entityDao;
	
	@Transactional
	public void addEntity(T entity) {
		entityDao.addEntity(entity);
	}
	
	@Transactional
    public void removeEntity(Class<T> entity, Integer id) {
		entityDao.removeEntity(entity, id);
	}
	
	@Transactional
    public List<T> listEntities(Class<T> entity) {
		return entityDao.listEntities(entity);
	}
	
	@Transactional
    public void updateEntity(T entity) {
		entityDao.updateEntity(entity);
	}
	
	@Transactional
    public T getEntityById(Class<T> entity, Integer entityId) {
		return entityDao.getEntityById(entity, entityId);
	}
}
