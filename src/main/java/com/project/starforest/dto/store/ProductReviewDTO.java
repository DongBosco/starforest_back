package com.project.starforest.dto.store;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//리뷰DTO
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//static: 내부클래스로 외부클래스의 종속되지않음.(독립적으로사용가능)
public class ProductReviewDTO {
	private Long id;
	private Long userId;
	private Long productId;
	private String content;
	private LocalDateTime createdAt;
};