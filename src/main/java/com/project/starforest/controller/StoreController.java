package com.project.starforest.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.starforest.domain.Product;
import com.project.starforest.domain.ProductReview;
import com.project.starforest.domain.ShoppingCartItem;
import com.project.starforest.domain.UserInfo;
import com.project.starforest.dto.ProductDTO;
import com.project.starforest.service.ProductService;


@RestController
@Log4j2
@RequestMapping("/store")
@Controller
public class StoreController {
	
	private ProductService productService;
	
	//get*******************************************************************
	//특정ID로 제품을 조회
	@GetMapping("/{id}")
	public ProductDTO getProduct(@PathVariable Long id) {
		return productService.getProductById(id);
	}
	
	//모든 제품조회
	@GetMapping
	public List<ProductDTO> getAllProducts() {
		return productService.getAllProducts();
	}
	
	//제품ID로 제품 세부정보 조회
	@GetMapping("/view/{productId}")		
	public Product getProductById(@PathVariable Long productId) {	//prductId라는 이름의 경로 변수를 받아와서 해당 제품의 정보를 반환
		log.info("상품 상세 정보 조회: 상품id = {}",productId);	//로그남김/ 상품id와 함께 조회 작업이 시작됨을 기록.
		return productService.getProductById(productId);
	}
	
//	@GetMapping("/list")
//	public List<ProductImageList> getProductList() {
//		log.info("전체 상품 목록 조회");
//		return productService.getAllProducts();
//	}
	
	//제품ID로 리뷰조회
	@GetMapping("/review/{productId}")
	public List<ProductReview> getProductReviews(@PathVariable Long productId) {
		log.info("상품 리뷰 조회: 상품 ID ={}", productId);
		return productService.getReviewsByProductId(productId);
	}
	
//	@GetMapping("/review")
//	public ProductReview addReview(@RequestBody ProductReview review) {
//		log.info("새 리뷰추가: {}",review);
//		return productService.addReview(review);
//	}
//	
//	@GetMapping("/user/{userId}")
//	public UserInfo getUserInfo(@PathVariable Long userId) {
//		log.info("사용자 정보 조회: 사용자 ID = {}", userId);
//		return productService.getUserInfoById(userId);
//	}
	
	
	//post******************************************************************
	//새로운리뷰추가
	@PostMapping("/review")
	public ProductReview addReview(@RequestBody ProductReview review) {
		log.info("새리뷰추가:{}",review);
		return productService.addReview(review);
	}
	
//	@PostMapping("/cart/add")
//	public ShoppingCartItem addToCart(@RequestBody ShoppingCartItem item) {
//		log.info("Adding item to cart:{}",item);
//		return productService.addItemToCart(item);
//	}
//	

}