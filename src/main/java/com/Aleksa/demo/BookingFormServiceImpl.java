package com.Aleksa.demo;

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

}
