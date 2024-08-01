package com.project.starforest.service.impl;

import com.project.starforest.service.KakaoPayService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.project.starforest.dto.pay.KakaoPayReadyResponse;
import com.project.starforest.dto.pay.PaymentApprovalResponse;
import com.project.starforest.dto.reservation.ReservationInfoDTO;
import com.project.starforest.dto.store.KakaoStoreRequestDTO;
import com.project.starforest.dto.store.KakaoSuccessRequestDTO;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@NoArgsConstructor
public class KakaoPayServiceImpl implements KakaoPayService {
	@Value("${kakao.admin.key}")
    private String adminKey;
	
    private static final String HOST = "https://kapi.kakao.com";
    private KakaoPayReadyResponse kakaoPayReadyResponse;
    
    public KakaoPayReadyResponse kakaoPayReady(ReservationInfoDTO dto,Long reservId) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + adminKey);
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", dto.getReservNum());//주문번호dto.getReservNum()
        params.add("partner_user_id", "gorany");//회원아이디dto.getNames()
        params.add("store_name", "🏞🏞별숲🏞🏞");// 매장 이름 	!!!고정
        params.add("item_name", dto.getName());//상품 이름
        params.add("quantity", "1");//수량					!!!고정
        params.add("total_amount", dto.getTotalPrice());//총 결제 금액
        params.add("tax_free_amount", "100");//비과세			!!!고정
        params.add("approval_url", 
        		"http://localhost:3000/camp/pay/complete/"
        				+dto.getReservNum()+"/"+reservId+"/"+dto.getNames()+"/"
        				+dto.getCar_number()+"/"+dto.getRequest()+"/"+dto.getTel());
        params.add("cancel_url", "http://localhost:3000/camp/pay/cancel");
        params.add("fail_url", "http://localhost:3000/camp/pay/fail");

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, headers);
        
        kakaoPayReadyResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/ready",
                requestEntity,
                KakaoPayReadyResponse.class);
        
        return kakaoPayReadyResponse;
    }
    
    
    
    public PaymentApprovalResponse approvePayment(String pgToken,String reservNum) {
    	
    	if (kakaoPayReadyResponse == null) {
            log.error("kakaoPayReadyResponse is null");
            throw new IllegalStateException("kakaoPayReadyResponse is null. Cannot approve payment.");
        }
    	log.info("kakaoPayReadyResponse : "+kakaoPayReadyResponse);
        // 移댁뭅�삤�럹�씠 寃곗젣 �듅�씤 API �샇異�
    	 RestTemplate restTemplate = new RestTemplate();

         HttpHeaders headers = new HttpHeaders();
         headers.add("Authorization", "KakaoAK " + adminKey);
         headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

         MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
         params.add("cid", "TC0ONETIME");
         params.add("tid", kakaoPayReadyResponse.getTid());
         params.add("partner_order_id", reservNum);
         params.add("partner_user_id", "gorany");
         params.add("pg_token", pgToken);

         HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);
         
         log.info("body"+body);
         
         
         return restTemplate.postForObject(HOST + "/v1/payment/approve", body, PaymentApprovalResponse.class);
     }



	@Override
	public KakaoPayReadyResponse StorekakaoPayReady(KakaoStoreRequestDTO dto, String combined) {
		 RestTemplate restTemplate = new RestTemplate();

		 int totalPrice = dto.getQuantity() * dto.getPrice(); 
		 
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Authorization", "KakaoAK " + adminKey);
	        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

	        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
	        params.add("cid", "TC0ONETIME");
	        params.add("partner_order_id", combined);//주문번호dto.getReservNum()
	        params.add("partner_user_id", "gorany");//회원아이디dto.getNames()
	        params.add("store_name", "🏞🏞별숲🏞🏞");// 매장 이름 	!!!고정
	        params.add("item_name", dto.getProduct_name());//상품 이름
	        params.add("quantity", dto.getQuantity());//수량					!!!고정
	        params.add("total_amount", totalPrice);//총 결제 금액
	        params.add("tax_free_amount", "100");//비과세			!!!고정
	        params.add("approval_url", 
	        		"http://localhost:3000/store/pay/complete/"
	        				+dto.getId()+"/"+combined+"/"+totalPrice+"/"+dto.getTel()+"/"
	        				+dto.getName()+"/"+dto.getAddress()+"/"+dto.getAddressDetail());
	        params.add("cancel_url", "http://localhost:3000/camp/pay/cancel");
	        params.add("fail_url", "http://localhost:3000/camp/pay/fail");

	        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, headers);
	        
	        kakaoPayReadyResponse = restTemplate.postForObject(
	                "https://kapi.kakao.com/v1/payment/ready",
	                requestEntity,
	                KakaoPayReadyResponse.class);
	        
	        return kakaoPayReadyResponse;
	}



	@Override
	public PaymentApprovalResponse approveStorePayment(String pgToken, KakaoSuccessRequestDTO dto) {
		if (kakaoPayReadyResponse == null) {
            log.error("kakaoPayReadyResponse is null");
            throw new IllegalStateException("kakaoPayReadyResponse is null. Cannot approve payment.");
        }
    	log.info("kakaoPayReadyResponse : "+kakaoPayReadyResponse);
        // 移댁뭅�삤�럹�씠 寃곗젣 �듅�씤 API �샇異�
    	 RestTemplate restTemplate = new RestTemplate();

         HttpHeaders headers = new HttpHeaders();
         headers.add("Authorization", "KakaoAK " + adminKey);
         headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

         MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
         params.add("cid", "TC0ONETIME");
         params.add("tid", kakaoPayReadyResponse.getTid());
         params.add("partner_order_id", dto.getOrderId());
         params.add("partner_user_id", "gorany");
         params.add("pg_token", pgToken);

         HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);
         
         log.info("body"+body);
         
         
         return restTemplate.postForObject(HOST + "/v1/payment/approve", body, PaymentApprovalResponse.class);
	}

    }

