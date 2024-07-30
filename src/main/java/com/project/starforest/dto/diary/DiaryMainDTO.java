package com.project.starforest.dto.diary;

import java.time.LocalDateTime;
import java.util.List;

import com.project.starforest.domain.DiaryImage;

import lombok.Data;

@Data
public class DiaryMainDTO {
	private Long id;
	private String content;
	private String category;
	private LocalDateTime created_at;
	private List<DiaryImage> diaryImages;
}
