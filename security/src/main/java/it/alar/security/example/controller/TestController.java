package it.alar.security.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.alar.security.example.component.CurrentUser;
import it.alar.security.hasrole.HasRole;

@RestController
public class TestController {

	@Autowired
	private CurrentUser currentUser;
	
	@HasRole("B")
	@GetMapping("/filterme/admin")
	public String admin(){
		return "Hi admin!";
	}
	
	@GetMapping("/filterme/whoami")
	public String whoami(){
		return currentUser.getUser().getUsername();
	}
}
