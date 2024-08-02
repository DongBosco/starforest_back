package com.project.starforest.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	private Long id;
	private String productName;      
	private String productImageUrl;
	private int  quantity;
	private int totalPrice;
	private String  status; //주문상태
	
}
