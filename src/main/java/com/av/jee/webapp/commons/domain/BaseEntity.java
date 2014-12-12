package com.av.jee.webapp.commons.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Base entity type to hold common Id properly. To be extended.
 * @author Administrator
 *
 */
@MappedSuperclass
public class BaseEntity implements Serializable{

	
	private static final long serialVersionUID = 6651278116427966456L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

}
