package com.ufcg.sahce.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
@AllArgsConstructor
@Entity
public class Place {

    public Place() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String imageURL;
    private String restrict;
    @OneToMany(targetEntity = Schedule.class)
    @JoinColumn(name = "idSchedule")
    private List<Schedule> schedules;
    private String flAtivo;

}
