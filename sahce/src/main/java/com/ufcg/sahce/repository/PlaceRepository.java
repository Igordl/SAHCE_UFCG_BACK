package com.ufcg.sahce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ufcg.sahce.domain.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query(value = "select * from place u where fl_ativo='S' ", nativeQuery = true)
    List<Place> findAllActives();
}
