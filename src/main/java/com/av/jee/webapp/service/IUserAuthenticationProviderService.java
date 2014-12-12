package com.av.jee.webapp.service;

import com.av.jee.webapp.domain.UserEntity;

/**
 * 
 * Provides processing service to set user authentication session 
 * 
 * @author Administrator
 *
 */

public interface IUserAuthenticationProviderService {
	/**
	 * Process user authentication
	 * @param user
	 * @return
	 */
	boolean processUserAuthentication(UserEntity user);

}
