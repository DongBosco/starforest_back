package com.project.starforest.service;

import java.util.List;

import com.project.starforest.domain.Product;
import com.project.starforest.domain.ProductReview;
import com.project.starforest.dto.store.UserOrderResponseDTO;
import com.project.starforest.dto.store.UserOrderReviewRequestDTO;
import com.project.starforest.dto.store.UserReviewListResponseDTO;
import jakarta.transaction.Transactional;

@Transactional
public interface UserReviewService {

	public UserOrderResponseDTO getUserProduct(Long productId);
	
	public String userReviewSave(UserOrderReviewRequestDTO userOrderDTO);
	
	public List<UserReviewListResponseDTO> userReviewList(String email);
	
}
