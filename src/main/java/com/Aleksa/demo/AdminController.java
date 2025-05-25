package com.Aleksa.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

	private ContactFormService cfs;
	
	@Autowired
	public void setCfs(ContactFormService cfs) {
		this.cfs = cfs;
	}

	
	private AdminCredentialsService acs;	
	
	@Autowired
	public void setAcs(AdminCredentialsService acs) {
		this.acs = acs;
	}

	@GetMapping(path={"/dashboard"})
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

	@GetMapping("/changeCredentials")
	public String showChangeCredentialsForm() {
	    return "/changeCredentials";
	}

	@PostMapping(path={"admin/changeCredentials"})
	public String changeCredentials(@RequestParam("oldname") String oldname,
	        @RequestParam("newname") String newname,
	        @RequestParam("oldpassword") String oldpassword,
	        @RequestParam("newpassword") String newpassword,
	        RedirectAttributes redirectattribute) {
	    String result = acs.checkAdminCredentials(oldname, oldpassword);
	    if(result.equals("Success")) {
	    	result=acs.updateAdminCredentials(newname, newpassword, oldname);
	    	redirectattribute.addFlashAttribute("message",result);
	    }else {
	    	redirectattribute.addFlashAttribute("message",result);
	    }

	    return "redirect:/admin/changeCredentials";

	}



}
