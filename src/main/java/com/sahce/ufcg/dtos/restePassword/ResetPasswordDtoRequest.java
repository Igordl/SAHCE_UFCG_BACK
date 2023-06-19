package com.sahce.ufcg.dtos.restePassword;

import java.io.Serializable;

public class ResetPasswordDtoRequest {
    private String token;

    private String email;

    private String newPassword;

    public ResetPasswordDtoRequest(String email, String token, String newPassword) {
        this.token = token;
        this.email = email;
        this.newPassword = newPassword;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public String getNewPassword() {
        return newPassword;
    }

}
