package com.sahce.ufcg.dtos.notification;

import com.sahce.ufcg.models.MyUser;

import java.io.Serializable;

public class NotificationDtoRequest implements Serializable {
    private final String placeName;

    private final String email;

    private final String subject;
    private final String message;

    public NotificationDtoRequest(String email, String placeName, String subject, String message) {
        this.placeName = placeName;
        this.email = email;
        this.message = message;
        this.subject = subject;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

    public String getSubject() {
        return subject;
    }

}
