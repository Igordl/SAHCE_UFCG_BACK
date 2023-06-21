package com.ufcg.sahce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ufcg.sahce.domain.Schedule;
import com.ufcg.sahce.domain.Scheduling;

@Service
public interface SchedulingService {

    Scheduling create(Scheduling user);

    List<Scheduling> findAll();

    Scheduling findById(Long id);

    Scheduling update(Scheduling scheduling);

    void deleteById(Long id);

    List<Scheduling> findByUserId(Long id);

    List<Schedule> findByPlaceId(Long placeId);
}
