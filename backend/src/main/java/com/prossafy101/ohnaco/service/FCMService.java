package com.prossafy101.ohnaco.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;
import com.prossafy101.ohnaco.entity.noti.NotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;


@Service
public class FCMService {

    private static final Logger logger = LoggerFactory.getLogger(FCMService.class);


    public void send(final NotificationRequest notificationRequest) throws InterruptedException, ExecutionException {
        System.out.println("send");
        System.out.println(notificationRequest.getToken());
        Message message = Message.builder()
                .setToken(notificationRequest.getToken())
                .setWebpushConfig(WebpushConfig.builder().putHeader("ttl", "300")
                        .setNotification(new WebpushNotification(notificationRequest.getTitle(),
                                notificationRequest.getMessage()))
                        .build())
                .build();

        String response = FirebaseMessaging.getInstance().sendAsync(message).get();
        logger.info("Sent message: " + response);
    }

}
