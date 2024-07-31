package com.project.starforest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.project.starforest.dto.store.ProductDTO;
import com.project.starforest.dto.store.ProductReviewDTO;
import com.project.starforest.dto.store.requestCartDTO;
import com.project.starforest.service.ProductService;
import com.project.starforest.service.impl.ProductServiceImpl;


@RestController
@Log4j2
@RequestMapping("/store") //해당 컨트롤러의 모든 요청 경로가 /store로 시작됨을 정의
public class StoreController {
	
	@Autowired
	private ProductService productService;
	
	//get*******************************************************************

//	@GetMapping("/list/{type}")
//	public Map<String, Object> getAllProducts(
//			@PathVariable("type") int type
//			){
//		log.info(")))))))))))))))))))"+type);
//		//모든 제품을가져와서 products리스트에 저장
//		List<ProductDTO> products = productService.getAllProducts();
//		
//		
//		
//		log.info("qqqqqqqqqqqqqqq"+products);
//		Map<String, Object> response = new HashMap<>();
//		//products 리스트를 stores 라는 맵에 추가 
//		response.put("stores", products);
//		response.put("hasMore", true); 
//		return response;
//	}
	
	  // 특정 타입의 제품 조회
    @GetMapping("/list/{type}")
    public Map<String, Object> getProductsByType(@PathVariable("type") int type) {
        log.info("Request for products of type: " + type);
        
        List<ProductDTO> products = new ArrayList<ProductDTO>();
        // 특정 타입의 제품을 가져와서 products 리스트에 저장
        if(type>2) {
        	products = productService.getAllProducts();
        }
        else {        	
        	products= productService.getProductsByType(type);
        }

        log.info("Products of type " + type + ": " + products);
        Map<String, Object> response = new HashMap<>();
        // products 리스트를 stores 라는 맵에 추가
        response.put("stores", products);
        response.put("hasMore", true);
        return response;
    }
    
	
	//제품ID로 제품 세부정보 조회
	@GetMapping("/view/{productId}")		
	public ProductDTO getProductById(@PathVariable("productId") Long productId) {	//prductId라는 이름의 경로 변수를 받아와서 해당 제품의 정보를 반환
		log.info("상품 상세 정보 조회: 상품id = {}",productId);	//로그남김/ 상품id와 함께 조회 작업이 시작됨을 기록.
		return productService.getProductById(productId);	//ID로 제품을 조회하여 반환
	}
	// 제품 세부정보 조회
//    @GetMapping("/view/{productId}")
//    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
//        log.info("Fetching product details: productId = {}", productId);
//        Product product = productService.getProductById(productId);
//        
//        if (product != null) {
//            return ResponseEntity.ok(product);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
//	

	
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
	public ProductReview addReview(@RequestBody ProductReviewDTO review) {
		log.info("새리뷰추가:{}",review);
		return productService.addReview(review);
	}
	
	@PostMapping("/cart/add")
	public ResponseEntity<String> addToCart(@RequestBody requestCartDTO item) throws Exception{
		try{
			log.info("Adding item to cart:{}",item);
			return ResponseEntity.ok("카트등록 완료.");
		}catch(Exception e) {
			return ResponseEntity.badRequest().body("쇼핑카트 담는중 에러 발생");
		
	}
	}
	

}