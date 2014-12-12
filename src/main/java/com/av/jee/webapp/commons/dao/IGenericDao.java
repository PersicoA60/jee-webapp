package com.av.jee.webapp.commons.dao;
import java.io.Serializable;
import java.util.List;

/**
 *  Generic interface for Data Access Objects to be extended or implemented-
 *  Contains common persistence methods.
 *  
 * @author Administrator
 *
 */

public interface IGenericDao<T, ID extends Serializable> {
	T save(T entity);
	T update(T entity);
	void delete (T entity);
	T findById(ID id);
	List<T> findAll();
	void flush();

}
