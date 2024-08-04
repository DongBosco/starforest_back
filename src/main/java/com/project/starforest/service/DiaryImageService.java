package com.project.starforest.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.starforest.dto.DiaryImageDTO;

public interface DiaryImageService {
	List<String> saveImages(Long diaryId, List<MultipartFile> images);
	List<DiaryImageDTO> getImagesByDiaryId(Long diaryId);
	List<String> getImagesForStringByDiaryId(Long diaryId);
	void deleteImage(Long imageId);
	void deleteImagesByDiaryId(Long diaryId);
	void deleteImageByUrl(String imageUrl);
}

