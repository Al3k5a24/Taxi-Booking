package com.Aleksa.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarCrud extends JpaRepository<Car, Integer>{

	@Override
	public <S extends Car> S save(S entity);
	
	Optional<Car> findByModel(String model);
	
	Optional<Car> findByDriverId(int driverId);
}
