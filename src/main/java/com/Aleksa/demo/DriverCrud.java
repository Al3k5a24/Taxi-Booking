package com.Aleksa.demo;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverCrud extends JpaRepository<Driver, Integer>{

	@Override
	public <S extends Driver> S save(S entity);
	
	@Override
	public List<Driver> findAll();
	
	@Override
	public void deleteById(Integer id);
	
	Optional<Driver> findById(Integer id);
}
