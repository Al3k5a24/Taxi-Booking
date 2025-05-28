package com.Aleksa.demo;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
<<<<<<< HEAD
import org.springframework.messaging.simp.SimpMessagingTemplate;
=======
>>>>>>> 3370273bef81e31fd3f94ebd365bf87bfe0d6353
=======
>>>>>>> 3370273bef81e31fd3f94ebd365bf87bfe0d6353
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@org.springframework.stereotype.Controller
public class Controller {
	
	private ContactFormService cfs;
	private saveBookingFormService bfs;
	
	@Autowired
	public void setBfs(saveBookingFormService bfs) {
		this.bfs = bfs;
	}

	@Autowired
	public void setCfsi(ContactFormService cfs) {
		this.cfs = cfs;
	}

	@GetMapping(path = {"/", "/home", "/welcome", "/index"})
	public String welcomeView(HttpServletRequest req,Model m) {
		String requestURI=req.getRequestURI();
		m.addAttribute("mycurrentpage",requestURI);
		m.addAttribute("bookingForm",new bookingForm());
	    return "home"; 
	}
	
	@GetMapping("/login")
	public String adminLoginView(HttpServletRequest request, Model m) {
		
		//Proverava da li postoji globalni atribut "logout" i da li je true
        // Ako jeste, dodaje ga u Model da bi Thymeleaf mogao prikazati poruku 
		ServletContext servletContext=request.getServletContext();
		Object attribute=servletContext.getAttribute("logout");
		if(attribute instanceof Boolean) {
			m.addAttribute("logout",attribute);
			servletContext.removeAttribute("logout");
		}
		
		
		return "login";
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
		 m.addAttribute("contactform", new ContactForm());
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
			BindingResult bindingresult,Model m, RedirectAttributes redirectAttributes) {
		if(bindingresult.hasErrors()) {
			m.addAttribute("bindingresult",bindingresult);
			return "services";  
		}
		
		ContactForm savecfs=cfs.saveContactService(contactform);
		if(savecfs!=null) {
			redirectAttributes.addFlashAttribute("message","Message sent successfully!");
		}else {
			redirectAttributes.addFlashAttribute("message","Something went wrong!");
		}
		
	    return "redirect:/services"; 
	}
	
<<<<<<< HEAD
<<<<<<< HEAD
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
=======
>>>>>>> 3370273bef81e31fd3f94ebd365bf87bfe0d6353
=======
>>>>>>> 3370273bef81e31fd3f94ebd365bf87bfe0d6353
	@PostMapping("/home")
	public String bookingform(@Valid @ModelAttribute("bookingForm") bookingForm bookingform,
			BindingResult bindingresult,Model m, RedirectAttributes redirectAttributes) {
		if(bindingresult.hasErrors()) {
			m.addAttribute("bindingresult",bindingresult);
			return "/home";  
		}else if(bookingform.getAdult()+bookingform.getChildren()>4) {
			m.addAttribute("message","Total number of passengers can not pass 4!");
			return "/home"; 
		}
		System.out.println(bookingform);
		
		bookingForm savebfs=bfs.saveBookingForm(bookingform);
		if(savebfs!=null) {
			redirectAttributes.addFlashAttribute("message","Message sent successfully!");
<<<<<<< HEAD
<<<<<<< HEAD
			messagingTemplate.convertAndSend("/topic/notifications", "New reservation from " + bookingform.getName());
=======
>>>>>>> 3370273bef81e31fd3f94ebd365bf87bfe0d6353
=======
>>>>>>> 3370273bef81e31fd3f94ebd365bf87bfe0d6353
		}else {
			redirectAttributes.addFlashAttribute("message","Something went wrong!");
		}
		
	    return "redirect:/home"; 
	}
}
