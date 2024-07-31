package com.project.starforest.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.starforest.domain.Product;
import com.project.starforest.domain.ProductImage;
import com.project.starforest.domain.ProductReview;
import com.project.starforest.domain.ShoppingCartItem;
import com.project.starforest.domain.UserInfo;
import com.project.starforest.dto.store.ProductDTO;
import com.project.starforest.dto.store.ProductImagesDTO;
import com.project.starforest.dto.store.ProductReviewDTO;
import com.project.starforest.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Transactional
public interface ProductService {
	//*********************************안씀**************************************************************
//	PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO);
//  요청들어온 PageRequestDTO를 PageResponseDTO로 반환

//	Long register(ProductDTO producDTO);
//	새로운 제품을 등록하고 생성된 제품의 Id를 반환
	
//	ProductDTO get(Long pno);
//	주어진 Id를 기반으로 제품 정보를 가져와서 DTO로 변환
	
//	void modify(ProductDTO productDTO);
//	제품DTO를 기반으로 제품 정보를 수정
	
//	void remove(Long pno);
//	주어진 ID를 기반으로 제품을 삭제	
	//*********************************안씀**************************************************************
	
	
	
	
	//주어진 getProductById값에 맞는 정보값을 찾아 반환
	ProductDTO getProductById(Long productId);
	
	 List<ProductDTO> getAllProducts();

	//모든 제품 목록을 반환
	List<ProductReview> getReviewsByProductId(Long productId);
	//getReviewsByProductId를 기반으로 해당 제품에 대한 모든 리뷰를 반환
	ProductReview addReview(ProductReviewDTO review);
	//새로운 리뷰를 추가하고 추가된 리뷰 객체를 반환.
	List<ProductDTO> getProductsByType(int type);
	
//	List<ProductImagesDTO> getImagesByProductId(Long productId);
//	UserInfo getUserInfoById(Long userId);
//	주어진 사용자ID에 대한 사용자 정보를 반환
	ShoppingCartItem addItemTocart(ShoppingCartItem item); 
//	쇼핑 카트에 항목을 추가하고 추가된 항목을 반환.
}