package com.project.starforest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.starforest.domain.Product;
import com.project.starforest.dto.store.UserOrderResponseDTO;
import com.project.starforest.repository.ProductRepository;
import com.project.starforest.service.UserReviewService;

@Service
public class UserReviewServiceImpl implements UserReviewService{

	@Autowired
	private ProductRepository productRepository;
	
	public UserOrderResponseDTO getUserProduct(Long productId) {

		Product productEntity = productRepository.findById(productId).orElseThrow();
		
		UserOrderResponseDTO userOrderDTO = UserOrderResponseDTO.builder()
				.brandName(productEntity.getBrand_name())
				.productName(productEntity.getProduct_name())
				.imgUrl(productEntity.getImgUrls())
				.build();
		return userOrderDTO;
	}
}
