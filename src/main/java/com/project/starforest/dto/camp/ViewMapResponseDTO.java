package com.project.starforest.dto.camp;

import java.util.List;

import com.project.starforest.domain.CampImage;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ViewMapResponseDTO {
	
    private Long id;
	private String name;
    private String lineIntro;
    private String intro;
    private Integer allar;
    private String featureNm;
    private Boolean is_glamp;
    private Boolean is_carvan;
    private Boolean is_auto;
    private String sigunguNm;
    private Double mapx;
    private Double mapy;
    private String tel;
    private String homepage;
    private Integer price;
    private Boolean animalCmgCl;
    private String glampInnerFclty;
    private String caravInnerFclty;
    private String posblFcltyCl;
    private String themaEnvrnCl;
    private String sbrsCl;
    private String eqpmnLendCl;
    private String first_image_url;
    private Boolean isCaravan;
    private String add1;
    private String add2;
    private String brazierCl;
    private List<CampImage> campImages;
}
