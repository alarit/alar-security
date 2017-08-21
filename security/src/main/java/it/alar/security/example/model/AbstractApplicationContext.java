package it.alar.security.example.model;

import java.util.HashSet;
import java.util.Set;

abstract class AbstractApplicationContext implements ApplicationContext {

	@Override
	public Set<String> getRoles() {
		return new HashSet<>();
	}
}
