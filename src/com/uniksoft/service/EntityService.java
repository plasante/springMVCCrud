package com.uniksoft.service;

import java.util.List;

public interface EntityService<T> {

	public void addEntity(T entity);
	public void updateEntity(T entity);
	public List<T> listEntities(Class<T> entity);
	public T getEntityById(Class<T> entity, Integer entityId);
	public void removeEntity(Class<T> entity, Integer entityId);
}
