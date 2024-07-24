package com.project.starforest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CampSearchDTO {
	private Long id;
	private String name;
    private String first_image_url;
    private Boolean is_glamp;
    private Boolean is_carvan;
    private Boolean is_auto;
    private Integer price;
    private String sigungu_nm;
}
