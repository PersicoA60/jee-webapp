package com.av.jee.webapp.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.av.jee.webapp.dao.IUserDao;
import com.av.jee.webapp.domain.UserEntity;
import com.av.jee.webapp.service.IUserService;

/**
 * Service providing service Methods to work with user data and entity.
 * 
 * @author Administrator
 *
 */

public class UserServiceImpl implements IUserService, UserDetailsService {

	private IUserDao userDao;

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public boolean createUser(UserEntity userEntity) {

		if (!userDao.checkAvailable(userEntity.getUserName())) {
			FacesMessage message = constructErrorMessage(String.format("Username '%s' is not available",
					userEntity.getUserName()), null);
			getFacesContext().addMessage(null, message);
			
			return false;
		}
		try {
			userDao.save(userEntity);
			
		} catch (Exception e) {
			
			FacesMessage message = constructFatalMessage(e.getMessage(), null);
			getFacesContext().addMessage(null, message);
			return false;
		}
		return true;
	}

	/**
	 * Construct UserFetails instance required by spring-security
	 */

	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {

		UserEntity user = userDao.loadUserByUserName(userName);

		if (user == null) {
			throw new UsernameNotFoundException(String.format(
					"No such user with name provided '%s'", userName));
		}

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		User userDetails = new User(user.getUserName(), user.getPassword(),
				authorities);

		return userDetails;

	}

	protected FacesMessage constructErrorMessage(String msg, String detail) {
		return new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, detail);
	}
	
	protected FacesMessage constructInfoMessage(String msg, String detail) {
		return new FacesMessage(FacesMessage.SEVERITY_INFO, msg, detail);
	}
	
	protected FacesMessage constructFatalMessage(String msg, String detail) {
		return new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, detail);
	}
	
	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
}
