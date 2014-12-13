package com.av.jee.webapp.service;

import javax.faces.event.AjaxBehaviorEvent;

import com.av.jee.webapp.domain.UserEntity;

	/**
	 * Service providing service methods to work with user data and entity
	 * 
	 * @author Administrator
	 *
	 */

	public interface IUserService {

	/**
	 * Create User - persist to database
	 * 
	 * @param userEntity
	 * @return true if success
	 * 
	 */

	boolean createUser(UserEntity userEntity);
	
	/**
	 * Check user name availability. UI ajax use
	 * 
	 * @param event 
	 * @return true if success
	 * 
	 */
	boolean checkAvailable(AjaxBehaviorEvent event);
	
	/**
	 * Retrieves full User record from database by user name
	 * 
	 * @param userEntity
	 * @return true if success
	 * 
	 */
	UserEntity loadUserEntityByUserName(String userName);

}
