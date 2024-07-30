package com.project.starforest.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//이미지DTO
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImagesDTO {
	private Long id;
	private String imageUrl;
	private int imageIndex;
	private String productNm;
//	private Date createdAt;
};