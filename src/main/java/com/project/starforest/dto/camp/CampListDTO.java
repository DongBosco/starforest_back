package com.project.starforest.dto.camp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CampListDTO {
	private Long id;
  private String name;
  private Boolean is_glamp;
  private Boolean is_carvan;
  private Boolean is_auto;
  private String add1;
  private Integer price;
  private String first_image_url;
  private String thema_envrn_cl;

}
