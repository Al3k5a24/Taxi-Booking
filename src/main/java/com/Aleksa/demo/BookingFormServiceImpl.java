package com.Aleksa.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingFormServiceImpl implements saveBookingFormService {

	private BookingFormCrud bookingFormCrud;
	
	@Autowired
	public void setBookingFormCrud(BookingFormCrud bookingFormCrud) {
		this.bookingFormCrud = bookingFormCrud;
	}

	@Override
	public bookingForm saveBookingForm(bookingForm bookingform) {
		return bookingFormCrud.save(bookingform);
	}

	@Override
	public List<bookingForm> readAllBooking() {
		// TODO Auto-generated method stub
		return bookingFormCrud.findAll();
	}

	@Override
	public void deleteBookingService(int id) {
		bookingFormCrud.deleteById(id);
		
	}

<<<<<<< HEAD
<<<<<<< HEAD
	@Override
	public void save(bookingForm reservation) {
		// TODO Auto-generated method stub
		
	}

=======
>>>>>>> 3370273bef81e31fd3f94ebd365bf87bfe0d6353
=======
>>>>>>> 3370273bef81e31fd3f94ebd365bf87bfe0d6353
}
