package com.project.starforest.repository;


import com.project.starforest.domain.ReservationDates;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationDates, Long>{
	
	 @Query("SELECT r FROM ReservationDates r WHERE (r.start_date <= :endDate AND r.end_date >= :startDate)")
	    List<ReservationDates> findOverlappingReservations(
	        @Param("startDate") LocalDateTime startDate,
	        @Param("endDate") LocalDateTime endDate
	    );
}
