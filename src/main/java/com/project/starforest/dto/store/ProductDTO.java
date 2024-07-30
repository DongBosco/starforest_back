package com.project.starforest.dto.store;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	private Long productId;
	private String productName;
	private String brandName;
	private int type;
	private int price;
	private String sale;
	private int slaesVolume; //누적판매량
	
	private String first_img_url;
//	private List<ProductImage> imageList;
	private List<ProductImagesDTO> imageList;
	private List<ProductReviewDTO> productReview;
	
	private String starsale;
	private String delivery;


	@Builder.Default
	private List<String> images = new ArrayList<>();
};



	
