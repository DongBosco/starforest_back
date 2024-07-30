package com.project.starforest.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.starforest.domain.ProductImage;

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
	private String del_flag;	//
	
	private String first_img_url;
//	private List<ProductImage> imageList;
	private List<ProductImage> imageList;
	private List<ProductReviewDTO> productReview;
	
//	private String starsale;
//	private String delivery;


	@Builder.Default
	private List<String> images = new ArrayList<>();
};



	
