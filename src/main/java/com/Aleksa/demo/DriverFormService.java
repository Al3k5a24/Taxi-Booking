package com.Aleksa.demo;

import java.util.List;

public interface DriverFormService {

	public Driver saveDriverService(Driver driver);
	public List<Driver> displayDriverService();
	public void deleteDriver(int id);
	
}
