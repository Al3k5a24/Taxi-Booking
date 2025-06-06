package com.Aleksa.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
public class BookingController {

    private final saveBookingFormService sbfs;
    private final NotificationController notificationController;

    @Autowired
    public BookingController(saveBookingFormService sbfs, NotificationController notificationController) {
        this.sbfs = sbfs;
        this.notificationController = notificationController;
    }

    @PostMapping("/sendReservation")
    public String sendReservation(@ModelAttribute bookingForm reservation) {
        sbfs.save(reservation);  // Sačuvaj rezervaciju u bazu

        // Pošalji notifikaciju adminu preko WebSocket-a
        String notificationMsg = "Nova rezervacija od " + reservation.getName() + ", tel: " + reservation.getEmail();
        notificationController.sendReservationNotification(notificationMsg);

        return "redirect:/success";  // ili gde želiš
    }
}
