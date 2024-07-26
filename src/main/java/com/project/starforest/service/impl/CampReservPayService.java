package com.project.starforest.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.starforest.domain.CampSite;
import com.project.starforest.domain.ReservationDates;
import com.project.starforest.dto.ReservationDto;
import com.project.starforest.repository.MapTestRepository;
import com.project.starforest.repository.ReservationRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CampReservPayService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private MapTestRepository mapTestRepository;
	
	public ResponseEntity<ReservationDates> getIsCampReservation(ReservationDto dto, Long id) {

		LocalDateTime startDate = dto.getStartDate();
	   	LocalDateTime endDate = dto.getEndDate();
	   	
	    if (isReservation(startDate, endDate,id)) {
        	ReservationDates faleReservation = new ReservationDates();
        	faleReservation.setStart_date(startDate);
        	faleReservation.setEnd_date(endDate);
        	faleReservation.setMessage("��..������ ���� �����߳׿�Ф�");
        	log.info("�����ߺ������ߺ������ߺ������ߺ������ߺ������ߺ������ߺ�");
        	return ResponseEntity.ok().body(faleReservation);
        }
		
	    CampSite campResult = mapTestRepository.findById(id).orElseThrow();
        log.info("ķ����"+campResult);
        if(campResult != null) {	
        	ReservationDates entity = ReservationDates.builder()
        			.start_date(startDate)
        			.end_date(endDate)
        			.created_at(LocalDateTime.now())
        			.message("���༺��!!")
        			.campSite(campResult)
        			.build();
        	
        	log.info("���༺�����༺�����༺�����༺�����༺��"+entity);
	    ReservationDates saveDate = reservationRepository.save(entity);
        	
        	log.info("���༺�����༺�����༺�����༺�����༺��"+saveDate);
        	return ResponseEntity.ok(saveDate);
        }
	    
        log.info("ķ�������ķ�������ķ�������ķ�������ķ�������");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // ķ������ ã�� ���� ��츦 ����� ����
	}
	
	private boolean isReservation(LocalDateTime start, LocalDateTime end, Long id) {
        List<ReservationDates> overlappingReservations = reservationRepository.findOverlappingReservations(start, end,id);
        return !overlappingReservations.isEmpty();
    }
	
}
