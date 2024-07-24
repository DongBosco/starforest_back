package com.project.starforest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CampReservationInfoDTO {
    private Long id;
    private Boolean is_glamp;
    private Boolean is_carvan;
    private Boolean is_auto;
    private String first_image_url;
	private String name;
	private String add1;
    private Double mapx;
    private Double mapy;
    private String sigungu_nm;
}
