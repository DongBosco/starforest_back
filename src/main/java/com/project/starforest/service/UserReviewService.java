package com.project.starforest.service;

import com.project.starforest.domain.Product;
import com.project.starforest.dto.store.UserOrderResponseDTO;

public interface UserReviewService {

	public UserOrderResponseDTO getUserProduct(Long productId);
}
