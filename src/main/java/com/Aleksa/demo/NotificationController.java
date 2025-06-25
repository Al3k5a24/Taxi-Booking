package com.Aleksa.demo;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public NotificationController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    // Metoda koju ćeš pozvati kada se napravi nova rezervacija
    public void sendReservationNotification(String message) {
    	simpMessagingTemplate.convertAndSend("/topic/notifications", message);
    }
}

