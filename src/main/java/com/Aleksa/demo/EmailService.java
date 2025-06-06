package com.Aleksa.demo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendReservationNotification(
            String to, String subject, String name, String email, 
            LocalTime time, String date, String from, String toDestination) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            String html = "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<style>" +
                    "  body { font-family: Arial, sans-serif; background-color: #1e1e1e; padding: 20px; color: #ffffff; }" +
                    "  .container { background-color: #2c2c2c; padding: 20px; border-radius: 10px; max-width: 600px; margin: auto; }" +
                    "  h2 { color: #f1c40f;text-align: center; }" +
                    "  p { color: #ffffff; font-size: 16px; }" +
                    "  table { width: 100%; border-collapse: collapse; margin-top: 20px; }" +
                    "  th, td { text-align: left; padding: 10px; border-bottom: 1px solid #444; color: #ffffff; }" +
                    "  th { background-color: #3a3a3a; }" +
                    "  td { background-color: #2c2c2c; }" +
                    "  .footer { text-align: center; margin-top: 30px; font-size: 12px; color: #aaaaaa; }" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<div class='container'>" +
                    "<h2>📅 New Booking Received</h2>" +
                    "<p>Thank you for booking a ride with us! Here are the details of your reservation:</p>" +

                    "<table>" +
                    "<tr><th>Field</th><th>Data</th></tr>" +
                    "<tr><td>Name</td><td>" + name + "</td></tr>" +
                    "<tr><td>Email</td><td>" + email + "</td></tr>" +
                    "<tr><td>Date</td><td>" + date + "</td></tr>" +
                    "<tr><td>Time</td><td>" + time + "</td></tr>" +
                    "<tr><td>Pickup Location</td><td>" + from + "</td></tr>" +
                    "<tr><td>Destination</td><td>" + toDestination + "</td></tr>" +
                    "</table>" +

                    "<p class='footer'>This message was automatically generated by the Taxi Booking System.</p>" +
                    "</div>" +
                    "</body>" +
                    "</html>";


            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true); // Enable HTML content
            mailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}


