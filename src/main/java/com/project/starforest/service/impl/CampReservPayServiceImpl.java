package com.project.starforest.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.project.starforest.service.CampReservPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.starforest.domain.CampSite;
import com.project.starforest.domain.Reservation;
import com.project.starforest.domain.ReservationDates;
import com.project.starforest.dto.reservation.ReservationDto;
import com.project.starforest.repository.MapTestRepository;
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
	
	public ResponseEntity<Reservation> getIsCampReservation(ReservationDto dto, Long id) {

		LocalDateTime startDate = dto.getStartDate();
	   	LocalDateTime endDate = dto.getEndDate();
	   	
	    if (isReservation(startDate, endDate,id)) {
        	Reservation faleReservation = new Reservation();
        	faleReservation.setStart_date(startDate);
        	faleReservation.setEnd_date(endDate);
        	faleReservation.setMessage("��..������ ���� �����߳׿�Ф�");
        	log.info("�����ߺ������ߺ������ߺ������ߺ������ߺ������ߺ������ߺ�");
        	return ResponseEntity.ok().body(faleReservation);
        }
		
	    CampSite campResult = mapTestRepository.findById(id).orElseThrow();
	    
	    //����ѹ�
//	    LocalDateTime now = LocalDateTime.now();
//	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
//        String formattedDate = now.format(formatter);
//        double random = Math.random()*100;
//        int randomInt = (int)Math.floor(random);
//        String combined = formattedDate + randomInt;
//        log.info(combined);
	    
        log.info("ķ����"+campResult);
        if(campResult != null) {	
        	Reservation entity = Reservation.builder()
        			.start_date(startDate)
        			.end_date(endDate)
        			.created_at(LocalDateTime.now())
        			.message("���� �����մϴ�!!")
        			.campsite_id(campResult)
//        			.reservation_number(combined)
        			.build();
        	
        	log.info("���༺�����༺�����༺�����༺�����༺��"+entity);
	    Reservation saveDate = reservation.save(entity);
        	
        	log.info("���༺�����༺�����༺�����༺�����༺��"+saveDate);
        	return ResponseEntity.ok(saveDate);
        }
	    
        log.info("ķ�������ķ�������ķ�������ķ�������ķ�������");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // ķ������ ã�� ���� ��츦 ����� ����
	}
	
	public boolean isReservation(LocalDateTime start, LocalDateTime end, Long id) {
        List<ReservationDates> overlappingReservations = reservationRepository.findOverlappingReservations(start, end,id);
        return !overlappingReservations.isEmpty();
    }
	
}
