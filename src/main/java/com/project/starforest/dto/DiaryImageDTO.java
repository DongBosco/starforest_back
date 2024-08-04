package com.project.starforest.dto;

//import java.sql.Timestamp;
import java.time.LocalDateTime;

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
	private LocalDateTime created_at;
}

