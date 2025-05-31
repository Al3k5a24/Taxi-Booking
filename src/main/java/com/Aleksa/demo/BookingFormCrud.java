package com.Aleksa.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingFormCrud extends JpaRepository<bookingForm, Integer>{

	@Override
	public <S extends bookingForm> S save(S entity);
	
	@Override
	public List<bookingForm> findAll();
	
	@Override
	public void deleteById(Integer id);
<<<<<<< HEAD
<<<<<<< HEAD
	
	List<bookingForm> findAllByOrderByIdDesc();

=======
>>>>>>> 3370273bef81e31fd3f94ebd365bf87bfe0d6353
=======
>>>>>>> 3370273bef81e31fd3f94ebd365bf87bfe0d6353
}
