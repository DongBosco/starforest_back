package com.project.starforest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.starforest.domain.Member;
import com.project.starforest.domain.Reservation;

public interface ReservationedRepository extends JpaRepository<Reservation, Long>{

	@Query("SELECT r FROM Reservation r WHERE r.member = :member")
	 List<Reservation> findByEmail(@Param("member") Member member);

}
