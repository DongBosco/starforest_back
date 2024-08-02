package com.project.starforest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.project.starforest.domain.UserInfo;
import com.project.starforest.dto.pay.KakaoPayReadyResponse;
import com.project.starforest.dto.store.KakaoStoreRequestDTO;
import com.project.starforest.dto.store.KakaoSuccessRequestDTO;
import com.project.starforest.dto.store.ProductDTO;
import com.project.starforest.dto.store.ProductResponseDTO;
import com.project.starforest.dto.store.ProductReviewDTO;
import com.project.starforest.dto.store.requestCartDTO;
import com.project.starforest.service.KakaoPayService;
import com.project.starforest.service.OrderKakaoService;
import com.project.starforest.service.ProductService;
import com.project.starforest.service.impl.ProductServiceImpl;


@RestController
@Log4j2
@RequestMapping("/store") //해당 컨트롤러의 모든 요청 경로가 /store로 시작됨을 정의
public class StoreController {
	
	@Autowired
	private final ProductService productService;
	
  	@Autowired
	private KakaoPayService kakaoPayService;
	
	@Autowired
	private OrderKakaoService orderKakaoService;
  
	public StoreController(ProductService productService) {
		this.productService = productService;
	}

		//get*******************************************************************

//		log.info("qqqqqqqqqqqqqqq"+products);
//		Map<String, Object> response = new HashMap<>();
//		//products 리스트를 stores 라는 맵에 추가 
//		response.put("stores", products);
//		response.put("hasMore", true); 
//		return response;
//	}
	
  // 특정 타입의 제품 조회            ㄹㅈㅂ
    @GetMapping("/list/{type}")
    public ResponseEntity<Map<String, Object>> getProductsByType(@PathVariable("type") int type) {
        log.info("Request for products of type: " + type);
        
        List<ProductDTO> products;
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
        return ResponseEntity.ok(response);  
    }
    
	
	//제품ID로 제품 세부정보 조회
	@GetMapping("/view/{productId}")		
	public ProductDTO getProductById(@PathVariable("productId") Long productId) {	//prductId라는 이름의 경로 변수를 받아와서 해당 제품의 정보를 반환
		log.info("상품 상세 정보 조회: 상품id = {}",productId);	//로그남김/ 상품id와 함께 조회 작업이 시작됨을 기록.
		return productService.getProductById(productId);	//ID로 제품을 조회하여 반환
	}
	

	//제품ID로 리뷰조회
//	@GetMapping("/review/{productId}")
//	public List<ProductReview> getProductReviews(@PathVariable Long productId) {
//		log.info("상품 리뷰 조회: 상품 ID ={}", productId);
//		List<ProductReview> reviews = productService.getReviewsByProductId(productId);
//		return productService.getReviewsByProductId(productId);
//	}
//	
	//제품ID로 리뷰조회
    @GetMapping("/review/{productId}")
    public ResponseEntity<List<ProductReview>> getProductReviews(@PathVariable Long productId) {
        log.info("Fetching reviews for productId = {}", productId);
        List<ProductReview> reviews = productService.getReviewsByProductId(productId);
        return ResponseEntity.ok(reviews);
    }
	
//	@GetMapping("/user/{userId}")
//	public UserInfo getUserInfo(@PathVariable Long userId) {
//		log.info("사용자 정보 조회: 사용자 ID = {}", userId);
//		return productService.getUserInfoById(userId);
//	}
	
	
	//userController로이동******************************************************************
	//새로운리뷰추가
//    @PostMapping("/review")
//    public ResponseEntity<ProductReview> addReview(@RequestBody ProductReviewDTO reviewDTO) {
//    	log.info("새 리뷰 추가: {}", reviewDTO);
//        try {
//            //리뷰작성자격검증부분 
//            boolean isEligible = productService.checkReviewEligibility(reviewDTO.getProductId(), reviewDTO.getOrderId(), reviewDTO.getUserEmail());
//           //리뷰작성 자격이 없을때 클라이언트한테 Forbidden에러 코드 반환
//            if (!isEligible) {
//               log.warn("사용자가 이 제품에 리뷰를 작성할 자격이 없습니다. Product ID: {}, Order ID: {}, User ID: {}",
//                reviewDTO.getProductId(), reviewDTO.getOrderId(), reviewDTO.getUserEmail());
//            	return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//	}
//            
//            ProductReview saveReview = productService.addReview(reviewDTO);
//            return ResponseEntity.status(HttpStatus.CREATED).body(saveReview);
//        } catch (Exception e) {
//            log.error("리뷰 추가 중 오류 발생: {}", e.getMessage(), e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

            

	
	//동일 작업시작
	//
	
// 	//스토어 구매전 데이터
// 	@PostMapping("/buy/product")
// 	public String storeBuy() {
// 		return null;

	//구매 페이지에서 상품 데이터 가져오기
	@GetMapping("/get/product/{productId}")
	public ResponseEntity<ProductResponseDTO> storeBuy(
			@PathVariable("productId") Long productId
			) {
		
		ProductResponseDTO result = productService.getProductByIdOfEntity(productId);
		
		return ResponseEntity.ok(result);
	}
	
	//스토어 카카오페이 결제 요청
	@PostMapping("/kakaoPay")
	public KakaoPayReadyResponse kakaoPay(@RequestBody KakaoStoreRequestDTO dto) {
		
		LocalDateTime now = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDate = now.format(formatter);
        double random = Math.random()*100;
        int randomInt = (int)Math.floor(random);
        String combined = formattedDate + randomInt;
        log.info("123123123123"+combined);
		log.info(dto.toString());
        KakaoPayReadyResponse result = kakaoPayService.StorekakaoPayReady(dto,combined);
		return result;
	}
	
	//카카오 결제 승인
	@PostMapping("/kakaoPaySuccess/{pg_token}")
	public String kakaopaySuccess(
			@PathVariable("pg_token") String pg_token,
			@RequestBody KakaoSuccessRequestDTO dto
			) {

		log.info(dto.toString());
		
		kakaoPayService.approveStorePayment(pg_token, dto);
		log.info("store 결제 완료!!");
		
		//order_Table에 저장
		orderKakaoService.OrderKakaoSave(dto);
		
		//order_info에 저장
		orderKakaoService.OrderInfoKakaoSave(dto);
		return null;
	}
	//
	//동일 작업 끝

}