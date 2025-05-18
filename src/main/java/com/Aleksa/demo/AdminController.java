package com.Aleksa.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("admin")
public class AdminController {

	@GetMapping(path={"/dashboard","/login"})
	public String AboutView(HttpServletRequest req,Model m) {
		String requestURI=req.getRequestURI();
		m.addAttribute("mycurrentpage",requestURI);
	    return "/dashboard"; 
	}
}
