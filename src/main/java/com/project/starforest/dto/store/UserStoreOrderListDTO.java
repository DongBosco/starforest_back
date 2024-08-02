package com.project.starforest.dto.store;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserStoreOrderListDTO {
	private LocalDateTime created_at;
	private Long orderId;
	private String orderNum;
	private String brandName;
	private Long productId;
	private int price;
	private String productName;
	private String imageUrl;
	private int totalPrice;
}
