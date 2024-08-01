package com.project.starforest.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KakaoStoreRequestDTO {
	private Long id;
	private String product_name;
	private String brand_name;
	private int price;
	private int quantity;
	private boolean type;
	private String address;
	private String addressDetail;
	private String tel;
	private String name;
	private String imgUrls;
}
