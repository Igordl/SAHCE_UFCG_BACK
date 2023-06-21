package com.ufcg.sahce.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import com.ufcg.sahce.domain.user.Guest;

import lombok.*;

@Builder
@Data
@ToString
@AllArgsConstructor
@Entity
public class Scheduling {

    public Scheduling() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long idUser;
    private Long idPlace;
    private Long idSchedule;
    private String flAtivo;
    @OneToMany(targetEntity = Guest.class)
    @JoinColumn(name = "id")
    private List<Guest> guests;

}
