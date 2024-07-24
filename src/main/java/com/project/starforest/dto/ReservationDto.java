package com.project.starforest.dto;

import java.time.LocalDateTime;

import com.project.starforest.domain.CampSite;
import com.project.starforest.domain.ReservationDates;

import lombok.Builder;
import lombok.Data;

@Data
//@Builder
public class ReservationDto {
	private Long id;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private LocalDateTime createdAt;
	
	private CampSite campsite;


	
	public ReservationDto() {};
	
	public ReservationDto(LocalDateTime startDate, LocalDateTime endDate,LocalDateTime createdAt) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
    }
	
	//entity�솕
	public ReservationDates toEntity() {
		return new ReservationDates(null,null, startDate,endDate,createdAt, null);
	}
}
