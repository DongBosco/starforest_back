package com.project.starforest.dto.store;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDTO {
	private Long id;
	private String product_name;
	private String brand_name;
	private int price;
	private int type;
	private String imgUrls;
}
