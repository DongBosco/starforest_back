 package com.project.starforest.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.starforest.domain.Product;
import com.project.starforest.domain.ProductImage;
import com.project.starforest.domain.ProductReview;
import com.project.starforest.domain.UserInfo;
import com.project.starforest.dto.store.ProductDTO;
import com.project.starforest.dto.store.ProductImagesDTO;
import com.project.starforest.dto.store.ProductResponseDTO;
import com.project.starforest.dto.store.ProductReviewDTO;
import com.project.starforest.dto.store.ProductReviewListResponseDTO;
import com.project.starforest.repository.ProductRepository;

import jakarta.transaction.Transactional;
         
@Transactional
public interface ProductService {

	
	//productId, orderId, userId로 리뷰작성 자격 검증
	boolean checkReviewEligibility(Long productId, Long orderId, String useEmail);
	
//	//리뷰 추가
//	ProductReview addReview(ProductReview review);
	
	//주어진 getProductById값에 맞는 정보값을 찾아 반환
	ProductDTO getProductById(Long productId);
	
	//모든제품목록을 반환
	 List<ProductDTO> getAllProducts();

	//해당제품에 모든리뷰반환
	 List<ProductReviewListResponseDTO> getReviewsByProductId(Long productId);
	
	//getReviewsByProductId를 기반으로 해당 제품에 대한 모든 리뷰를 반환
	ProductReview addReview(ProductReviewDTO reviewDTO);
	
	//새로운 리뷰를 추가하고 추가된 리뷰 객체를 반환.
	List<ProductDTO> getProductsByType(int type);

	//userEmail로 리뷰조회
	static List<ProductReview> getReviewsByUserEmail(String userEmail) {
		return null;
	}
	
//	List<ProductImagesDTO> getImagesByProductId(Long productId);
//	UserInfo getUserInfoById(Long userId);
//	주어진 사용자ID에 대한 사용자 정보를 반환
//	ShoppingCartItem addItemTocart(ShoppingCartItem item);
//	쇼핑 카트에 항목을 추가하고 추가된 항목을 반환.
	
	
	
	
	//동일 작성
	ProductResponseDTO getProductByIdOfEntity(Long productId);
	//동일 작성
	
}