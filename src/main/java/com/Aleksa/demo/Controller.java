package com.Aleksa.demo;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

	@GetMapping(path = {"/", "/home", "/welcome", "/index"})
	public String welcomeView() {
	    return "home"; 
	}

	@GetMapping("/about")
	public String AboutView() {
	    return "about"; 
	}

	@GetMapping("/Cars")
	public String CarsView() {
	    return "Cars";  
	}

	@GetMapping("/Services")
	public String ServiceView() {
	    return "Services";  
	}
	
	@GetMapping("/Contact")
	public String ContactView() {
	    return "Contact";  
	}
}
