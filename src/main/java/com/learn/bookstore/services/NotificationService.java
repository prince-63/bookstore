package com.learn.bookstore.services;

import com.learn.bookstore.models.Notification;

import java.util.List;

public interface NotificationService {

    void sendNotification(Long userId, String message);

    List<Notification> getUserNotifications(Long userId);

    void markAsRead(Long notificationId);

}

