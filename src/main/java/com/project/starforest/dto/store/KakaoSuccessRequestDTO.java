package com.project.starforest.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KakaoSuccessRequestDTO {
	private Long productId;
	private String add;
	private String addDetail;
	private String name;
	private String orderId;
	private String tel;
	private Integer totalPrice;
	private String email;
}
