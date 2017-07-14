package it.alar.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping("/pippo/topolino")
	public String testLogin(){
		LOGGER.info("Got it");
		return "Authenticated";
	}
	
	@RequestMapping("/login/pippo/pluto")
	public String filterMe(){
		LOGGER.info("Got it");
		return "Filtering";
	}
}