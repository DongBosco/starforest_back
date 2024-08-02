package com.project.starforest.service;

import com.project.starforest.dto.pay.KakaoPayReadyResponse;
import com.project.starforest.dto.pay.PaymentApprovalResponse;
import com.project.starforest.dto.reservation.ReservationInfoDTO;
import com.project.starforest.dto.store.KakaoStoreRequestDTO;
import com.project.starforest.dto.store.KakaoSuccessRequestDTO;

public interface KakaoPayService {

    KakaoPayReadyResponse kakaoPayReady(ReservationInfoDTO dto, Long reservId);
    
    KakaoPayReadyResponse StorekakaoPayReady(KakaoStoreRequestDTO dto, String combined);

    PaymentApprovalResponse approvePayment(String pgToken, String reservNum);
    
    PaymentApprovalResponse approveStorePayment(String pgToken, KakaoSuccessRequestDTO dto);
}

