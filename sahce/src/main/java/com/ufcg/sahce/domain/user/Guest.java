package com.ufcg.sahce.domain.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
@AllArgsConstructor
@Entity
public class Guest {
    public Guest() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idGuest;
    private String name;
    private String email;
    private Long id;
}
