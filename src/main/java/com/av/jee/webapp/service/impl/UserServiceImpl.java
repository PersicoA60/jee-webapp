package com.av.jee.webapp.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.component.inputtext.InputText;
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
			FacesMessage message = constructErrorMessage(String.format(getMessageBundle().getString("userExistMsg"),
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
					getMessageBundle().getString("badCredentials"), userName));
		}

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		User userDetails = new User(user.getUserName(), user.getPassword(),
				authorities);

		return userDetails;

	}
	
	/**
	 * Check user name availability. UI ajax use
	 * 
	 * @param userEntity
	 * @return true if success
	 * 
	 */
	public boolean checkAvailable(AjaxBehaviorEvent event) {

		InputText inputText = (InputText) event.getSource();
		String value = (String) inputText.getValue();
		boolean available  = userDao.checkAvailable(value);
		if (!available) {
			FacesMessage message = constructErrorMessage(null,
					String.format("Username '%s' is not available", value));
			getFacesContext().addMessage(event.getComponent().getClientId(), message);
			
		}else {
			FacesMessage message = constructInfoMessage(null, String.format(getMessageBundle().getString("userAvailableMsg"), value));
			getFacesContext().addMessage(event.getComponent().getClientId(), message);
		}
		return false;
	}
	
	/**
	 * Retrieves full User record from database by user name
	 * 
	 * @param userEntity
	 * @return true if success
	 * 
	 */
	public UserEntity loadUserEntityByUserName(String userName) {
		return userDao.loadUserByUserName(userName);
	}

	protected ResourceBundle getMessageBundle () {
		return ResourceBundle.getBundle("message-labels");
		
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
