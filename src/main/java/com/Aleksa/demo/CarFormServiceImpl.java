package com.Aleksa.demo;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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

	@Override
	public boolean existsById(int id) {
		return true;
	}

	@Override
	public List<Car> displayCar() {
		return cc.findAll();
	}

	@Override
	public Optional<Car> findByDriverId(Integer driverId) {
	    return cc.findByDriverId(driverId);
	}2
}
