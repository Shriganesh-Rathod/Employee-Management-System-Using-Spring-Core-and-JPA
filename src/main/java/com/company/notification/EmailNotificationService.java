package com.company.notification;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class EmailNotificationService implements NotificationService {

    @PostConstruct
    public void initialize() {
        System.out.println("EmailNotificationService bean initialized.");
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Sending Email Notification");
        System.out.println("Email Notification: " + message);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("EmailNotificationService bean is being destroyed.");
    }
}