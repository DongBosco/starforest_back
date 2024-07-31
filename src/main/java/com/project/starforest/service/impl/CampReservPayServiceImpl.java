package com.project.starforest.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.project.starforest.service.CampReservPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.starforest.domain.CampSite;
import com.project.starforest.domain.Member;
import com.project.starforest.domain.Reservation;
import com.project.starforest.domain.ReservationDates;
import com.project.starforest.dto.reservation.ReservationDto;
import com.project.starforest.repository.MapTestRepository;
import com.project.starforest.repository.MemberRepository;
import com.project.starforest.repository.ReservationRepository;
import com.project.starforest.repository.ReservationedRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CampReservPayServiceImpl implements CampReservPayService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private MapTestRepository mapTestRepository;
	
	@Autowired
	private ReservationedRepository reservation;
	
	@Autowired
	private MemberRepository memberRepository;
	
	public ResponseEntity<Reservation> getIsCampReservation(ReservationDto dto, Long id,String email) {

		LocalDateTime startDate = dto.getStartDate();
	   	LocalDateTime endDate = dto.getEndDate();
	   	
	    if (isReservation(startDate, endDate,id)) {
        	Reservation faleReservation = new Reservation();
        	faleReservation.setStart_date(startDate);
        	faleReservation.setEnd_date(endDate);
        	faleReservation.setMessage("앗...누군가 이미 예약했네요!!");
        	return ResponseEntity.ok().body(faleReservation);
        }
		
	    CampSite campResult = mapTestRepository.findById(id).orElseThrow();
	    
	    Member memberEntity = memberRepository.findByEmail(email);
	    log.info(memberEntity.getEmail());
	    log.info("%%%%%%%%%%%%%%%%%%%%%%%%"+memberEntity.toString());
        
        if(campResult != null) {	
        	Reservation entity = Reservation.builder()
        			.start_date(startDate)
        			.end_date(endDate)
        			.created_at(LocalDateTime.now())
        			.message("예약 가능합니다!!")
        			.campsite_id(campResult)
        			.member(memberEntity)//멤버 이메일 넣어야함
        			.build();
        	
	    Reservation saveDate = reservation.save(entity);
        	
        	return ResponseEntity.ok(saveDate);
        }
	    
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // ķ������ ã�� ���� ��츦 ����� ����
	}
	
	public boolean isReservation(LocalDateTime start, LocalDateTime end, Long id) {
        List<ReservationDates> overlappingReservations = reservationRepository.findOverlappingReservations(start, end,id);
        return !overlappingReservations.isEmpty();
    }
	
}
