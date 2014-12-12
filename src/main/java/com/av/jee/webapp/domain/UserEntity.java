package com.av.jee.webapp.domain;



import javax.persistence.Entity;

import javax.persistence.Table;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.av.jee.webapp.commons.domain.BaseEntity;




/**
 * Entity to hold application user data firsName....
 */
@Entity
@Table(name="appuser")
public class UserEntity extends BaseEntity{
	
	private static final long serialVersionUID = 9195257090696221687L;
	
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	
	
	

	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		Md5PasswordEncoder crypto = new Md5PasswordEncoder();
		this.password = crypto.encodePassword(password, null);
	}

	
	

}
