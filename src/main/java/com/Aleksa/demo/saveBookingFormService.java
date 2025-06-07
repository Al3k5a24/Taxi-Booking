package com.Aleksa.demo;

import java.util.List;

public interface saveBookingFormService {

	public bookingForm saveBookingForm(bookingForm bookingform);
	public List<bookingForm> readAllBooking();
	public void deleteBookingService(int id);
	public void save(bookingForm reservation);
}
