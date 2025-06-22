package com.Aleksa.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@org.springframework.stereotype.Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Ako želiš da šalješ poruku svima na /topic/notifications
    public void sendNotification(String message) {
        messagingTemplate.convertAndSend("/topic/notifications", message);
    }

    // Ako želiš da klijent šalje poruku na /app/home
    @MessageMapping("/home")
    public void receiveMessage(@Payload String message) {
        // Ova metoda može proslediti poruku negde ako treba
        messagingTemplate.convertAndSend("/topic/notifications", message);
    }
}
