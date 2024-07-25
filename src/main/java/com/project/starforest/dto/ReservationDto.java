package com.project.starforest.dto;

import java.time.LocalDateTime;

import com.project.starforest.domain.CampSite;
import com.project.starforest.domain.ReservationDates;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
	private Long id;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private LocalDateTime createdAt;

	
}
