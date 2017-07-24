package it.alar.security.example.model;

import java.util.Set;

import it.alar.security.ApplicationContext;

public class MyContext implements ApplicationContext {

	private String username;
	private Set<String> roles;
	
	public String getUsername() {
		return username;
	}

	public MyContext setUsername(String username) {
		this.username = username;
		return this;
	}

	@Override
	public Set<String> getRoles() {
		return roles;
	}

	@Override
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

}
