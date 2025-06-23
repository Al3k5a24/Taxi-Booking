package com.Aleksa.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface AdminCrud extends JpaRepository<Admin, Integer>{

	Optional<Admin> findByUsername(String username);
	
	@Modifying
	@Transactional
	@Query(value="update admin set username = :newname, password = :newpassword where username = :oldname", nativeQuery = true)
	public int updateCredentials(
	    @Param("newname") String newname,
	    @Param("newpassword") String newpassword,
	    @Param("oldname") String oldname);

}
