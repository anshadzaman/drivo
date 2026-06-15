package com.drivo.service;

import com.drivo.entity.Notification;
import com.drivo.entity.User;
import com.drivo.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository
            notificationRepository;

    @Autowired
    private UserService userService;

    public void createNotification(
            User user,
            String message) {

        Notification notification =
                new Notification();

        notification.setUser(user);

        notification.setMessage(
                message
        );

        notification.setRead(false);

        notification.setCreatedAt(
                LocalDateTime.now()
        );

        notificationRepository.save(
                notification
        );
    }

    public List<Notification>
    getMyNotifications(
            String email
    ) {

        User user =
                userService
                        .getUserByEmail(
                                email
                        );

        return notificationRepository
                .findByUserIdOrderByCreatedAtDesc(
                        user.getId()
                );
    }

}