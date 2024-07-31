package com.project.starforest.service;

import com.project.starforest.dto.pay.KakaoPayReadyResponse;
import com.project.starforest.dto.pay.PaymentApprovalResponse;
import com.project.starforest.dto.reservation.ReservationInfoDTO;

public interface KakaoPayService {

    KakaoPayReadyResponse kakaoPayReady(ReservationInfoDTO dto, Long reservId);

    PaymentApprovalResponse approvePayment(String pgToken, String reservNum);
}

