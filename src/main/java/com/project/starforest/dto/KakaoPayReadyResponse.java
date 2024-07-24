package com.project.starforest.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class KakaoPayReadyResponse {
	private String tid;	//결제 고유번호
    private String next_redirect_pc_url;	//pc일때 받는 페이지
    private String created_at;
}
