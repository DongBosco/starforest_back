package com.project.starforest.dto.reservation;

import lombok.Data;

@Data
public class ReservationInfoDTO {
	private Long id;
	private String reservNum;
	private String name;
	private int price;
	private int totalPrice;
	private String car_number;
	private String names;
	private String request;
	private String tel;
}
