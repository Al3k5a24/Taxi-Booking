package com.Aleksa.demo;

import org.springframework.beans.factory.annotation.Autowired;

public class CarFormServiceImpl implements CarFormService{

	private CarCrud cc;
	
	@Autowired
	public void setCc(CarCrud cc) {
		this.cc = cc;
	}
	
	@Override
	public Car saveCarService(Car car) {
		return cc.save(car);
	}

}
