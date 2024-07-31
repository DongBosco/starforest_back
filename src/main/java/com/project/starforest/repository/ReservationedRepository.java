package com.project.starforest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.starforest.domain.Reservation;

public interface ReservationedRepository extends JpaRepository<Reservation, Long>{

}
