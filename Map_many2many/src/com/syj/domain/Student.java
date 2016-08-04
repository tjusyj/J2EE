package com.syj.domain;

import java.util.Set;

public class Student {

	private Integer id;
	private String name;
	private Set<Choose> choices;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Choose> getChoices() {
		return choices;
	}
	public void setChoices(Set<Choose> choices) {
		this.choices = choices;
	}
	
}
