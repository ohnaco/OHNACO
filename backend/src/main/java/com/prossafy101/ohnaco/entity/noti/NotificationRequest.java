package com.prossafy101.ohnaco.entity.noti;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

public class NotificationRequest {

    private String token;
    private String title;
    private String message;

    public NotificationRequest(String title, String message, String token) {
        this.token = token;
        this.title = title;
        this.message = message;
    }
}
