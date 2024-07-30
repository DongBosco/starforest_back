package com.project.starforest.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.project.starforest.domain.Diary;
import com.project.starforest.domain.DiaryImage;
import com.project.starforest.dto.DiaryImageDTO;
import com.project.starforest.repository.DiaryImageRepository;
import com.project.starforest.repository.DiaryRepository;
import com.project.starforest.service.DiaryImageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiaryImageServiceImpl implements DiaryImageService {

	private final DiaryImageRepository diaryImageRepository;
	private final DiaryRepository diaryRepository;
	
	@Value("${file.upload-dir}")
	private String uploadDir;
	
	@Override
	@Transactional
	public List<String> saveImages(Long diaryId, List<MultipartFile> images) {
		List<String> savedImageUrls = new ArrayList<>();
		
		Diary diary = diaryRepository.findById(diaryId)
				.orElseThrow(() -> new RuntimeException("diary not found with id: " + diaryId));
		
		for (MultipartFile image : images) {
			String imageUrl = uploadImageToServer(image);
			
			DiaryImage diaryImage = DiaryImage.builder()
					.diary(diary)
					.image_url(imageUrl)
					.build();
			diaryImageRepository.save(diaryImage);
			savedImageUrls.add(imageUrl);
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
	@Transactional
	public void deleteImage(Long imageId) {
        diaryImageRepository.deleteById(imageId);
	}
	
	
	@Override
	@Transactional
	public void deleteImagesByDiaryId(Long diaryId) {
		diaryImageRepository.deleteByDiaryId(diaryId);
	}
	
	
	private String uploadImageToServer(MultipartFile image) {
		try {
			Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
			Files.createDirectories(uploadPath);
			
			String fileName = StringUtils.cleanPath(image.getOriginalFilename());
			String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
			
			Path targetLocation = uploadPath.resolve(uniqueFileName);
			Files.copy(image.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			return "/uploads/" + uniqueFileName;
		}catch (IOException ex) {
			throw new RuntimeException("이미지 저장중 오류가 발생했습니다.", ex);
		}
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
