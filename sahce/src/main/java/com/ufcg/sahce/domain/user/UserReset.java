package com.ufcg.sahce.domain.user;

import lombok.*;

@Builder
@Data
@ToString
@AllArgsConstructor
public class UserReset {

    public UserReset() {
    }

    private String email;
    private String password;
    private String token;
}
