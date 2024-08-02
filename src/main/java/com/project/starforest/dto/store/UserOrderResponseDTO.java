package com.project.starforest.dto.store;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserOrderResponseDTO {
	private String productName;
	private String brandName;
	private String imgUrl;
}
