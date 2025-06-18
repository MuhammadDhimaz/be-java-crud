package com.example.springbootcrud.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendWelcomeEmail(String email, String name) {
        System.out.println("Sending welcome email to: " + email + " (name: " + name + ")");
    }
}
