package it.alar.security.model;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractApplicationContext implements ApplicationContext {

	@Override
	public Set<String> getRoles() {
		return new HashSet<>();
	}
}
