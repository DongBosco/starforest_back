package com.project.starforest.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.starforest.domain.CampSite;
import com.project.starforest.domain.Reservation;
import com.project.starforest.domain.ReservationDates;
import com.project.starforest.dto.ReservationDto;
import com.project.starforest.repository.MapTestRepository;
import com.project.starforest.repository.ReservationRepository;
import com.project.starforest.repository.ReservationedRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CampReservPayService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private MapTestRepository mapTestRepository;
	
	@Autowired
	private ReservationedRepository reservation;
	
	public ResponseEntity<Reservation> getIsCampReservation(ReservationDto dto, Long id) {

		LocalDateTime startDate = dto.getStartDate();
	   	LocalDateTime endDate = dto.getEndDate();
	   	
	    if (isReservation(startDate, endDate,id)) {
        	Reservation faleReservation = new Reservation();
        	faleReservation.setStart_date(startDate);
        	faleReservation.setEnd_date(endDate);
        	faleReservation.setMessage("앗..누군가 벌써 예약했네요ㅠㅠ");
        	log.info("예약중복예약중복예약중복예약중복예약중복예약중복예약중복");
        	return ResponseEntity.ok().body(faleReservation);
        }
		
	    CampSite campResult = mapTestRepository.findById(id).orElseThrow();
	    
	    //예약넘버
	    LocalDateTime now = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDate = now.format(formatter);
        double random = Math.random()*100;
        int randomInt = (int)Math.floor(random);
        String combined = formattedDate + randomInt;
        log.info(combined);
	    
        log.info("캠핑장"+campResult);
        if(campResult != null) {	
        	Reservation entity = Reservation.builder()
        			.start_date(startDate)
        			.end_date(endDate)
        			.created_at(LocalDateTime.now())
        			.message("예약 가능합니다!!")
        			.campsite_id(campResult)
        			.reservation_number(combined)
        			.build();
        	
        	log.info("예약성공예약성공예약성공예약성공예약성공"+entity);
	    Reservation saveDate = reservation.save(entity);
        	
        	log.info("예약성공예약성공예약성공예약성공예약성공"+saveDate);
        	return ResponseEntity.ok(saveDate);
        }
	    
        log.info("캠핑장없음캠핑장없음캠핑장없음캠핑장없음캠핑장없음");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 캠핑장을 찾지 못한 경우를 대비한 응답
	}
	
	private boolean isReservation(LocalDateTime start, LocalDateTime end, Long id) {
        List<ReservationDates> overlappingReservations = reservationRepository.findOverlappingReservations(start, end,id);
        return !overlappingReservations.isEmpty();
    }
	
}
