package com.project.starforest.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductReviewListResponseDTO {
	private String userNickName;
	private String content;
	private String profileUrl;
}
