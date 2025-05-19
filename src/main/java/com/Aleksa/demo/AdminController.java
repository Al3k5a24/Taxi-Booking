package com.Aleksa.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private ContactFormService cfs;
	
	@GetMapping(path={"/dashboard"})
	@GetMapping(path={"/dashboard","/login"})
	public String AboutView(HttpServletRequest req,Model m) {
		String requestURI=req.getRequestURI();
		m.addAttribute("mycurrentpage",requestURI);
	    return "/dashboard"; 
	}
	
	@GetMapping(path={"/readAllContacts"})
	public String readAllContacts(HttpServletRequest req,Model m) {
		String requestURI=req.getRequestURI();
		m.addAttribute("mycurrentpage",requestURI);
		m.addAttribute("allcontacts",cfs.displayContactService());
	    return "/readAllContacts"; 
	}
	
	@GetMapping(path={"admin/deleteContact/{id}"})
	public String deleteContact(@PathVariable("id") int id,RedirectAttributes redirectAttribute) {
		cfs.deleteContact(id);
		redirectAttribute.addFlashAttribute("Message","Contact successfully deleted!");
		 return "redirect:/admin/readAllContacts";
	}
}
