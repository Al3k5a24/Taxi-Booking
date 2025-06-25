package com.Aleksa.demo;

import java.util.List;
import java.util.Optional;

public interface CarFormService {

	public Car saveCarService(Car car);
	boolean existsById(int id);
	public List<Car> displayCar();
	Optional<Car> findByDriverId(Integer driverId);
}
