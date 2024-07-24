package com.project.starforest.service;
import java.util.List;

import com.project.starforest.domain.Product;
import com.project.starforest.domain.ProductReview;
import com.project.starforest.domain.ShoppingCartItem;
import com.project.starforest.domain.UserInfo;

import jakarta.transaction.Transactional;

@Transactional
public interface ProductService {
//	PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO);
//
//	Long register(ProductDTO producDTO);
//
//	ProductDTO get(Long pno);
//
//	void modify(ProductDTO productDTO);
//
//	void remove(Long pno);
	
	Product getProductById(Long productId);
	List<Product> getAllProducts();
	List<ProductReview> getReviewsByProductId(Long productId);
	ProductReview addReview(ProductReview review);
//	UserInfo getUserInfoById(Long userId);
//	ShoppingCartItem addItemTocart(ShoppingCartItem item); 
}
