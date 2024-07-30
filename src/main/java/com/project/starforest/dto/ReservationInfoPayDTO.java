package com.project.starforest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReservationInfoPayDTO {
	private Long id;
	private String reservNum;
	private String name;
	private int price;
}
