package com.ufcg.sahce.domain.user;

import lombok.*;

@Builder
@Data
@ToString
@AllArgsConstructor
public class MyUserLogin {
    public MyUserLogin() {

    }

    private String name;
    private String email;
    private String type;
    private String flAtivo;
}
