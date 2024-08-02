package com.project.starforest.repository;

import com.project.starforest.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.starforest.domain.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ReservationedRepository extends JpaRepository<Reservation, Long>{

    @Query("SELECT r " +
            "FROM Reservation r " +
            "WHERE r.member.email = :email")
    List<Reservation> findAllByEmail(@Param("email") String email);

    @Query("SELECT r " +
            "FROM Reservation r " +
            "WHERE r.id = :ReservId")
    Reservation findByReservId(@Param("ReservId") Long ReservId);
}
