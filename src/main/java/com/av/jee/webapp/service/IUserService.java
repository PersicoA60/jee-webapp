package com.av.jee.webapp.service;

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
	

}
