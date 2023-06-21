package com.ufcg.sahce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ufcg.sahce.domain.Schedule;
import com.ufcg.sahce.domain.Scheduling;

public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {

    @Query(value = "select * from scheduling u where u.id_user = :pId and fl_ativo='S' ", nativeQuery = true)
    List<Scheduling> findByUserId(@Param("pId") Long idUser);

    @Query(value = "select * from scheduling u where u.id_place = :pPlaceId and fl_ativo='S' ", nativeQuery = true)
    List<Schedule> findAllByPlaceId(@Param("pPlaceId") Long placeId);

}
