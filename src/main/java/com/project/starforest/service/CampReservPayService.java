package com.project.starforest.service;

import com.project.starforest.domain.CampSite;
import com.project.starforest.domain.Reservation;
import com.project.starforest.domain.ReservationDates;
import com.project.starforest.dto.reservation.ReservationDto;
import com.project.starforest.repository.MapTestRepository;
import com.project.starforest.repository.ReservationRepository;
import com.project.starforest.repository.ReservationedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface CampReservPayService {

    ResponseEntity<Reservation> getIsCampReservation(ReservationDto dto, Long id,String email);
    boolean isReservation(LocalDateTime start, LocalDateTime end, Long id);
}
