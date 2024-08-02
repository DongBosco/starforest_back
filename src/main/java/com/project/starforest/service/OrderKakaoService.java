package com.project.starforest.service;

import com.project.starforest.dto.store.KakaoSuccessRequestDTO;

public interface OrderKakaoService {

	String OrderKakaoSave(KakaoSuccessRequestDTO dto);

	void OrderInfoKakaoSave(KakaoSuccessRequestDTO dto);
}
