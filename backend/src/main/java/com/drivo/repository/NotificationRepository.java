package com.drivo.repository;

import com.drivo.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository
        extends JpaRepository<Notification, Long> {

    List<Notification>
    findByUserIdOrderByCreatedAtDesc(
            Long userId);
    long countByUserIdAndIsReadFalse(
            Long userId
    );
    List<Notification>
    findByUserIdAndIsReadFalse(
            Long userId
    );

}