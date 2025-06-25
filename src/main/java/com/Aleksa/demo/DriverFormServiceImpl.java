package com.Aleksa.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DriverFormServiceImpl implements DriverFormService{

	private DriverCrud dc;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void setDc(DriverCrud dc) {
		this.dc = dc;
	}
	
	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public Driver saveDriverService(Driver driver) {
		String encodedPassword=passwordEncoder.encode(driver.getPassword());
		driver.setPassword(encodedPassword);
		return dc.save(driver);
	}

	@Override
	public List<Driver> displayDriverService() {
		return dc.findAll();
	}

	@Override
	public void deleteDriver(int id) {
		dc.deleteById(id);
		
	}
	@Override
	public boolean existsByEmail(String email) {
		return true;
	}
}
