package com.ufcg.sahce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ufcg.sahce.domain.user.Guest;

public interface GuestRepository extends JpaRepository<Guest, Long> {

    @Query(value = "select * from guest u where lower(u.email) = lower(:pEmail)", nativeQuery = true)
    List<Guest> findByGuestEmail(@Param("pEmail") String email);
}
