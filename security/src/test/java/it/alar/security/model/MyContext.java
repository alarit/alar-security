package it.alar.security.model;

import java.util.Set;

public class MyContext extends AbstractApplicationContext {

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

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
}
