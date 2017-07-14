package it.alar.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping("/notfiltered")
	public String testLogin(){
		LOGGER.info("Got it");
		return "Unprotected URI";
	}
	
	@RequestMapping("/filterme/test")
	public String filterMeTest(){
		LOGGER.info("Got it");
		return "Filtering";
	}
	
	@RequestMapping("/filterme/anothertest")
	public String filterMeAnotherTest(){
		LOGGER.info("Got it");
		return "Filtering";
	}
}