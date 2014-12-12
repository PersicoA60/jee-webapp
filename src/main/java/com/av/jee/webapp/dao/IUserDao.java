package com.av.jee.webapp.dao;

import com.av.jee.webapp.commons.dao.IGenericDao;
import com.av.jee.webapp.domain.UserEntity;

/**
 *  Data access object interface to work with User entity database operations
 * @author Administrator
 *
 */

public interface IUserDao extends IGenericDao<UserEntity, Long> {
	
	 /**
     * Queries database for user name availability
     * 
     * @param userName
     * @return true if available
     */

    boolean checkAvailable(String userName);

    

    /**
     * Queries user by userName
     * 
     * @param userName
     * @return User entity
     */

    UserEntity loadUserByUserName(String userName);


	
	

}
