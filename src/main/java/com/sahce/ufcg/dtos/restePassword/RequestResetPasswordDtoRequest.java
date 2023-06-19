package com.sahce.ufcg.dtos.restePassword;

import java.io.Serializable;

public class RequestResetPasswordDtoRequest {
    private String email;

    public RequestResetPasswordDtoRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
