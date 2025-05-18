package com.Aleksa.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminCrud extends JpaRepository<Admin, Integer>{

	Optional<Admin> findByUsername(String username);
	
}
