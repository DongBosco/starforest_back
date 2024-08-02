package com.project.starforest.dto.store;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderViewRequestDTO {
	private String content;
	private Long productId;
	private String userEmail;
}
