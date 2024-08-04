package com.project.starforest.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiaryDTO {

	private Long id;
	private Long reservationId;
	private String userEmail;
	private String content;
	private String allTags;
	private LocalDateTime created_at;
	private List<String> image_url;
}
