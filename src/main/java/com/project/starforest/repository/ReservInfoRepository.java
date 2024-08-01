package com.project.starforest.repository;

import com.project.starforest.domain.Member;
import com.project.starforest.domain.Reservation;
import com.project.starforest.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.starforest.domain.ReservInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.security.cert.CertPathBuilder;

public interface ReservInfoRepository extends JpaRepository<ReservInfo, Long>{
    @Query("SELECT r " +
            "FROM ReservInfo r " +
            "WHERE r.reservation =:reservation")
    ReservInfo getReferenceByReserv(Reservation reservation);
}
