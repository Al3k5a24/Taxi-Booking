package com.Aleksa.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class UserDetailsServiceImp implements UserDetailsService{

	private AdminCrud admincrud;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	public void setAdmincrud(AdminCrud admincrud) {
		this.admincrud = admincrud;
	}
	
	//ako nema admina,kreira jednog admina
	@PostConstruct
	public void init() {
		long count=admincrud.count();
		if(count==0) {
			Admin admin=new Admin();
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("admin123"));
			
			admincrud.save(admin);
		}
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    Optional<Admin> byUsername = admincrud.findByUsername(username);
	    Admin admin = byUsername.orElseThrow(() -> new UsernameNotFoundException("Admin does not exist!"));

	    return org.springframework.security.core.userdetails.User
	            .withUsername(admin.getUsername())
	            .password(admin.getPassword()).authorities("ROLE_ADMIN")
	            .password(admin.getPassword())
	            .build();
	}

}
