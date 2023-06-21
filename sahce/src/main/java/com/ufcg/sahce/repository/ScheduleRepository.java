package com.ufcg.sahce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ufcg.sahce.domain.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query(value = "select * from schedule u where fl_ativo='S' ", nativeQuery = true)
    List<Schedule> findAll();

}
