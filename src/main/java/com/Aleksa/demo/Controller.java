package com.Aleksa.demo;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@org.springframework.stereotype.Controller
public class Controller {

	//added method to track site name that user visited
	//in html added thymeleaf method that sets class current if site is selected
	
	@GetMapping(path = {"/", "/home", "/welcome", "/index"})
	public String welcomeView(HttpServletRequest req,Model m) {
		String requestURI=req.getRequestURI();
		m.addAttribute("mycurrentpage",requestURI);
	    return "home"; 
	}

	@GetMapping("/about")
	public String AboutView(HttpServletRequest req,Model m) {
		String requestURI=req.getRequestURI();
		m.addAttribute("mycurrentpage",requestURI);
	    return "about"; 
	}

	@GetMapping("/cars")
	public String CarsView(HttpServletRequest req,Model m) {
		String requestURI=req.getRequestURI();
		m.addAttribute("mycurrentpage",requestURI);
	    return "cars";  
	}

	@GetMapping("/services")
	public String ServiceView(HttpServletRequest req,Model m) {
		String requestURI=req.getRequestURI();
		m.addAttribute("mycurrentpage",requestURI);
		 m.addAttribute("contactform", new ContactForm()); // Make sure this is added
	    return "services";  
	}
	
	@GetMapping("/contact")
	public String ContactView(HttpServletRequest req,Model m) {
		String requestURI=req.getRequestURI();
		m.addAttribute("mycurrentpage",requestURI);
	    return "contact";  
	}
	
	@PostMapping("/services")
	public String contactform(@Valid @ModelAttribute("contactform") ContactForm contactform,
			BindingResult bindingresult,Model m) {
		if(bindingresult.hasErrors()) {
			m.addAttribute("contactform", contactform);
			m.addAttribute("bindingresult",bindingresult);
			return "services";  
		}
	    System.out.println("Forma primljena: " + contactform);
	    return "services";  
	}
}
