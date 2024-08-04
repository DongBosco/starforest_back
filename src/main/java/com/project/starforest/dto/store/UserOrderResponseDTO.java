package com.project.starforest.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderResponseDTO {
	private String productName;
	private String brandName;
	private String imgUrl;
}
