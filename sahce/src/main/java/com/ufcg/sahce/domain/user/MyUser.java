package com.ufcg.sahce.domain.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;

@Builder
@Data
@ToString
@AllArgsConstructor
@Entity
public class MyUser {
    public MyUser() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String doc;
    private String docFile;
    private String address;
    private String userType;
    private String flAtivo;
}
