package it.alar.security.example.controller;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.alar.security.AlarSecurityManager;
import it.alar.security.SecurityContext;
import it.alar.security.model.MyContext;

@RestController
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private AlarSecurityManager securityManager;
	
	
	@GetMapping("/notfiltered")
	public String testLogin(){
		LOGGER.info("Got it");
		return "Unprotected URI";
	}
	
	@GetMapping("/filterme/test")
	public String filterMeTest(){
		LOGGER.info("Got it");
		return "Hi, this is Alar-security";
	}
	
	@GetMapping("/filterme/anothertest")
	public String filterMeAnotherTest(){
		LOGGER.info("Got it");
		return "Filtering";
	}
	
	/*
	 * Simulating an authentication, this mehod should recieve the context 
	 * and protect it in the security context
	 */
	@GetMapping("/authenticate")
	public void authenticateMe(){
		SecurityContext securityContext = new SecurityContext();
		MyContext context = new MyContext().setUsername("alar");
		Set<String> roles = new HashSet<>();
		roles.add("A");
		context.setRoles(roles);
		securityContext.setApplicationContext(context);
		securityManager.setAuthenticated(securityContext);
	}
}