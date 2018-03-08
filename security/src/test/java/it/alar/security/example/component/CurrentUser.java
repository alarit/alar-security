package it.alar.security.example.component;

import org.springframework.stereotype.Component;

import it.alar.security.ContextHolder;
import it.alar.security.model.MyContext;

@Component
public class CurrentUser {

	public MyContext getUser() {
		return (MyContext) ContextHolder.getContext().getApplicationContext();
	}
}
