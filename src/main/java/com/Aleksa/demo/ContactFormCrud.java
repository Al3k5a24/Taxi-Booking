package com.Aleksa.demo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactFormCrud extends JpaRepository<ContactForm, Integer>{

	@Override
	public <S extends ContactForm> S save(S entity);
	
	@Override
	public List<ContactForm> findAll();
	
	@Override
	public void deleteById(Integer id);
<<<<<<< HEAD
<<<<<<< HEAD
	
	List<ContactForm> OrderByIdDesc();
=======
>>>>>>> 3370273bef81e31fd3f94ebd365bf87bfe0d6353
=======
>>>>>>> 3370273bef81e31fd3f94ebd365bf87bfe0d6353
}
