package com.project.starforest.controller;


import com.project.starforest.dto.userInfo.ResReservListDTO;
import com.project.starforest.dto.userInfo.ResReservViewDTO;
import com.project.starforest.service.UserOrderLIstService;
import com.project.starforest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.starforest.domain.ProductReview;
import com.project.starforest.dto.store.OrderViewRequestDTO;
import com.project.starforest.dto.store.ProductReviewDTO;
import com.project.starforest.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.starforest.dto.store.UserStoreOrderListDTO;


@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/user")
public class UserInfoController {

    private final UserService userService;
    private final ProductService productService;
    private final UserOrderLIstService userOrderLIstService;
    @GetMapping("/camp/list")
    public List<ResReservListDTO> getMyReservList(@RequestParam(name="email") String email) throws Exception {
        try {
            List<ResReservListDTO> res = userService.getMyReservList(email);
            System.out.println(res);
            return res;
        }catch (Exception e){
            log.info("예약 조회중 오류 발생했습니다.");
            return null;
        }
    }

    @GetMapping("/camp/view")
    public ResponseEntity<?> getReservData(@RequestParam(name="reservationid") Long reservationid, @RequestParam(name="email") String email) {
        try {
            System.out.println("입력");
            ResReservViewDTO res = userService.getDetail(reservationid, email);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            log.info("예약 조회 중 오류 발생: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("예약 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
	//새로운리뷰추가???????????????????????????????????????????????????????
    @PostMapping("/store/order/view/{orderId}")
    public ResponseEntity<ProductReview> addReview(@RequestBody ProductReviewDTO reviewDTO) {
    	log.info("새 리뷰 추가: {}", reviewDTO);
        try {
            //리뷰작성자격검증부분
            boolean isEligible = productService.checkReviewEligibility(reviewDTO.getProductId(), reviewDTO.getOrderId(), reviewDTO.getUserEmail());
           //리뷰작성 자격이 없을때 클라이언트한테 Forbidden에러 코드 반환
            if (!isEligible) {
               log.warn("사용자가 이 제품에 리뷰를 작성할 자격이 없습니다. Product ID: {}, Order ID: {}, User ID: {}",
                reviewDTO.getProductId(), reviewDTO.getOrderId(), reviewDTO.getUserEmail());
            	return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}

            ProductReview saveReview = productService.addReview(reviewDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveReview);
        } catch (Exception e) {
            log.error("리뷰 추가 중 오류 발생: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

	//내가 작성한 리뷰 리스트조회
	@GetMapping("/review")
	public ResponseEntity<List<ProductReview>> getUserReview(@RequestBody OrderViewRequestDTO orderViewDTO){

		log.info("orderViewDTOorderViewDTO"+orderViewDTO.toString());

		 try {
	            List<ProductReview> reviews = ProductService.getReviewsByUserEmail(orderViewDTO.getUserEmail());
	            if (reviews.isEmpty()) {
	                log.info("사용자가 작성한 리뷰가 없습니다: {}", orderViewDTO.getUserEmail());
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	            }
	            return ResponseEntity.ok(reviews);
	        } catch (Exception e) {
	            log.error("리뷰 조회 중 오류 발생: {}", e.getMessage(), e);
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
    @GetMapping("/store/order/list/{email}")
    public ResponseEntity<List<UserStoreOrderListDTO>>  getAllOrderList(
            @PathVariable("email") String email
    ) {

        List<UserStoreOrderListDTO> result = userOrderLIstService.getAllOrderList(email);
        log.info("resultresultresultresult"+result.toString());
        return ResponseEntity.ok(result);
    }
}
