package com.project.starforest.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.project.starforest.domain.Diary;
import com.project.starforest.domain.DiaryImage;
import com.project.starforest.dto.DiaryImageDTO;
import com.project.starforest.repository.DiaryImageRepository;
import com.project.starforest.repository.DiaryRepository;
import com.project.starforest.service.DiaryImageService;
import com.project.starforest.service.S3FileUploadService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiaryImageServiceImpl implements DiaryImageService {

	private final DiaryImageRepository diaryImageRepository;
	private final DiaryRepository diaryRepository;
	private final S3FileUploadService s3FileUploadService;
	
	@Override
	@Transactional
	public List<String> saveImages(Long diaryId, List<MultipartFile> images) {
	    Diary diary = diaryRepository.findById(diaryId)
	            .orElseThrow(() -> new RuntimeException("diary not found with id: " + diaryId));
	    
	    List<String> savedImageUrls = new ArrayList<>();
	    for (MultipartFile image : images) {
	        try {
	            String imageUrl = s3FileUploadService.uploadFile(image);
	            DiaryImage diaryImage = DiaryImage.builder()
	                    .diary(diary)
	                    .image_url(imageUrl)
	                    .build();
	            diaryImageRepository.save(diaryImage);
	            savedImageUrls.add(imageUrl);
	        } catch (IOException e) {
	            throw new RuntimeException("Failed to upload image", e);
	        }
	    }
	    
	    return savedImageUrls;
	}
	

	

	
	@Override
	@Transactional(readOnly = true)
	public List<DiaryImageDTO> getImagesByDiaryId(Long diaryId) {
        return diaryImageRepository.findByDiaryId(diaryId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<String> getImagesForStringByDiaryId(Long diaryId) {
	    return diaryImageRepository.findByDiaryId(diaryId).stream()
	            .map(DiaryImage-> DiaryImage.getImage_url())  
	            .collect(Collectors.toList());
	}
	
	
	@Override
	@Transactional
	public void deleteImage(Long imageId) {
        diaryImageRepository.deleteById(imageId);
	}
	
	
	@Override
	@Transactional
	public void deleteImagesByDiaryId(Long diaryId) {
		diaryImageRepository.deleteByDiaryId(diaryId);
	}
	
	@Override
	@Transactional
	public void deleteImageByUrl(String imageUrl) {
        String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        s3FileUploadService.deleteFile(fileName);
        diaryImageRepository.deleteByImageUrl(imageUrl);
    }
	
	

	
	private DiaryImageDTO convertToDTO(DiaryImage diaryImage) {
        return DiaryImageDTO.builder()
                .id(diaryImage.getId())
                .diaryId(diaryImage.getDiary().getId())
                .image_url(diaryImage.getImage_url())
                .created_at(diaryImage.getCreated_at())
                .build();
    }
	
	
	

	
	
	
}
