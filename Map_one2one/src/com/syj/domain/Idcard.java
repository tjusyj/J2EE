package com.syj.domain;

import java.io.Serializable;
import java.util.Date;

public class Idcard implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date validate;
	private Person person;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getValidate() {
		return validate;
	}
	public void setValidate(Date validate) {
		this.validate = validate;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	
}
