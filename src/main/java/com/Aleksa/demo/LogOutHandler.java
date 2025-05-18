package com.Aleksa.demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogOutHandler implements LogoutHandler{

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		// TODO Auto-generated method stub
		
	}

}
