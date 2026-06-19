package com.drivo.service;

import com.drivo.entity.Notification;
import com.drivo.entity.User;
import com.drivo.enums.NotificationType;
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

    @Autowired
    private WebSocketNotificationService
            webSocketNotificationService;

    public void createNotification(
            User user,
            String message,
            NotificationType type) {

        Notification notification =
                new Notification();

        notification.setUser(user);

        notification.setMessage(
                message
        );
        notification.setType(type);
        notification.setRead(false);

        notification.setCreatedAt(
                LocalDateTime.now()
        );

        notificationRepository.save(
                notification
        );
        webSocketNotificationService
                .sendNotification(

                        user.getId(),

                        message

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
    public void markAsRead(
            Long id) {

        Notification notification =
                notificationRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Notification not found"
                                )
                        );

        notification.setRead(true);

        notificationRepository.save(
                notification
        );
    }
    public long getUnreadCount(
            String email) {

        User user =
                userService
                        .getUserByEmail(
                                email
                        );

        return notificationRepository
                .countByUserIdAndIsReadFalse(
                        user.getId()
                );
    }
    public void markAllAsRead(
            String email) {

        User user =
                userService
                        .getUserByEmail(
                                email
                        );

        List<Notification>
                notifications =
                notificationRepository
                        .findByUserIdAndIsReadFalse(
                                user.getId()
                        );

        for (
                Notification notification
                : notifications
        ) {

            notification
                    .setRead(true);

        }

        notificationRepository
                .saveAll(
                        notifications
                );

    }
    public void deleteNotifcation(Long id){
        notificationRepository.deleteById(id);
    }
    public void clearAllNotifications(
            String email) {

        User user =
                userService
                        .getUserByEmail(
                                email
                        );

        List<Notification>
                notifications =
                notificationRepository
                        .findByUserId(
                                user.getId()
                        );

        notificationRepository
                .deleteAll(
                        notifications
                );

    }
}