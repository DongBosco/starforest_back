package com.project.starforest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MapResponseDTO {
	
	private Long id;
	private String first_image_url;
	private String name;
	private String add1;
    private Integer price;
    private Boolean is_glamp;
    private Boolean is_carvan;
    private Boolean is_auto;
    private String homepage;
	private Double mapX;
	private Double mapY;
}
