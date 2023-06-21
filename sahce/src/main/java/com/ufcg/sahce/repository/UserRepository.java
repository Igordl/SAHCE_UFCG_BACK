package com.ufcg.sahce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ufcg.sahce.domain.user.MyUser;

public interface UserRepository extends JpaRepository<MyUser, Long> {

    @Query(value = "select * from my_user u where lower(u.email) = lower(:pEmail) and u.password = :pPassword", nativeQuery = true)
    Optional<MyUser> login(@Param("pEmail") String email, @Param("pPassword") String password);

    @Query(value = "select * from my_user u where lower(u.email) = lower(:pEmail)", nativeQuery = true)
    Optional<MyUser> findByEmail(@Param("pEmail") String email);

}
