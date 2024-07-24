package com.project.starforest.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.project.starforest.dto.KakaoPayReadyResponse;
import com.project.starforest.dto.PaymentApprovalResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KakaoPayService {
	@Value("${kakao.admin.key}")
    private String adminKey;
	
    private static final String HOST = "https://kapi.kakao.com";
    private KakaoPayReadyResponse kakaoPayReadyResponse;

    public KakaoPayReadyResponse kakaoPayReady() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + adminKey);
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", "1001");//가맹점 주문 번호
        params.add("partner_user_id", "gorany");//가맹점 회원 ID
        params.add("store_name", "여기어때"); // 이 줄을 추가하세요
        params.add("item_name", "가평 오롯하다글램핑 글램핑바다 6인");//상품명
        params.add("quantity", "1");//주문 수량
        params.add("total_amount", "427500");//총 금액
        params.add("tax_free_amount", "100");//상품 비과세 금액
        params.add("approval_url", "http://localhost:3000/camp/pay/complete");
        params.add("cancel_url", "http://localhost:3000/camp/pay/cancel");
        params.add("fail_url", "http://localhost:3000/camp/pay/fail");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        
        kakaoPayReadyResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/ready",
                requestEntity,
                KakaoPayReadyResponse.class);
        
        return kakaoPayReadyResponse;
    }
    
    
    
    public PaymentApprovalResponse approvePayment(String pgToken) {
    	log.info("kakaopayService -> approvePayment실행");
    	
    	if (kakaoPayReadyResponse == null) {
            log.error("kakaoPayReadyResponse is null");
            throw new IllegalStateException("kakaoPayReadyResponse is null. Cannot approve payment.");
        }
    	log.info("kakaoPayReadyResponse는 null이 아님");
    	log.info("kakaoPayReadyResponse : "+kakaoPayReadyResponse);
        // 카카오페이 결제 승인 API 호출
    	 RestTemplate restTemplate = new RestTemplate();

         HttpHeaders headers = new HttpHeaders();
         headers.add("Authorization", "KakaoAK " + adminKey);
         headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

         MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
         params.add("cid", "TC0ONETIME");
         params.add("tid", kakaoPayReadyResponse.getTid());
         params.add("partner_order_id", "1001");
         params.add("partner_user_id", "gorany");
         params.add("pg_token", pgToken);

         HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);
         
         log.info("body"+body);
         
         
         return restTemplate.postForObject(HOST + "/v1/payment/approve", body, PaymentApprovalResponse.class);
     }
    }

