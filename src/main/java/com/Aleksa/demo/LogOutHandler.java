package com.Aleksa.demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LogOutHandler implements LogoutHandler{

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		
		try {
			ServletContext servletcontext=request.getServletContext();
			servletcontext.setAttribute("logout",true);
			
			response.sendRedirect("admin/dashboard");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
