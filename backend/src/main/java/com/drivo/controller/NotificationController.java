package com.drivo.controller;

import com.drivo.entity.Notification;
import com.drivo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService
            notificationService;

    @GetMapping
    public List<Notification>
    getMyNotifications() {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return notificationService
                .getMyNotifications(
                        email
                );
    }
    @PutMapping("/{id}/read")
    public void markAsRead(
            @PathVariable Long id) {

        notificationService
                .markAsRead(id);

    }
    @GetMapping("/unread-count")
    public long getUnreadCount(){
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        return notificationService.getUnreadCount(email);

    }
    @PutMapping("/read-all")
    public void markAllAsRead() {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        notificationService
                .markAllAsRead(
                        email
                );

    }   

}