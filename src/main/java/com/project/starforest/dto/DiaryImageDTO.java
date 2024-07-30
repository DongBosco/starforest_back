package com.project.starforest.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiaryImageDTO {

	private Long id;
	private Long diaryId;
	private String image_url;
	private Timestamp created_at;
}

