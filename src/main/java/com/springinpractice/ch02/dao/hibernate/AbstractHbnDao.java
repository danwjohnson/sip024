/*
 * Spring in Practice Chapter 02
 * Recipe: 2.4
 * Class: AbstractHbnDao.java
 */
package com.springinpractice.ch02.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.util.ReflectionUtils;

import com.springinpractice.dao.Dao;

public abstract class AbstractHbnDao<T extends Object>
		implements Dao<T> {

	@Inject private SessionFactory sessionFactory;
	private Class<T> domainClass;
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	} // end getSession()
	
	
	@SuppressWarnings("unchecked")
	private Class<T> getDomainClass() {
		
		if (domainClass == null) {
			
			ParameterizedType thisType =
					(ParameterizedType) getClass().getGenericSuperclass();
			this.domainClass =
					(Class<T>) thisType.getActualTypeArguments()[0];
			
		} // end if
		
		return domainClass;
		
	} // end getDomainClass()
	
	
	private String getDomainClassName() {
		
		return getDomainClass().getName();
		
	} // end getDomainClassName()
	
	
	public void create(T t) {
		
		Method method = ReflectionUtils.findMethod(
				getDomainClass(), "setDateCreated", 
				new Class[] {Date.class});
		
		if (method != null) {
			
			try {
				
				method.invoke(t,  new Date());
				
			} // end try
			catch (Exception e) { /* Ignore */ }
			
		} // end if
		
		getSession().save(t);
		
	} // end create()
	
	
	
	@SuppressWarnings("unchecked")
	public T get(Serializable id) {
		
		return (T) getSession().get(getDomainClass(), id);
		
	} // end get()
	
	
	@SuppressWarnings("unchecked")
	public T load(Serializable id) {
		
		return (T) getSession().load(getDomainClass(), id);
		
	} // end load()
	
	
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		
		return getSession()
				.createQuery("from " + getDomainClassName())
				.list();
		
	} // end getAll()
	
	
	public void update(T t) {
		
		getSession().update(t);
		
	} // end update()
	
	
	public void delete(T t) {
		
		getSession().delete(t);
		
	} // end delete()
	
	
	public void deleteById(Serializable id) {
		
		delete(load(id));
		
	} // end deleteById()
	
	
	// TODO look at this.
	public void deleteAll() {
		
		getSession()
			.createQuery("delete " + getDomainClassName())
			.uniqueResult();
		
	} // end deleteAll()
	
	
	public long count() {
		
		return (Long) getSession()
				.createQuery("select count(*) from " + getDomainClassName())
				.uniqueResult();
		
	} // end count()
	
	
	public boolean exists(Serializable id) {
		
		return (get(id) != null);
		
	} // end exists()
	
} // end AbstractHbnDao abstract class
