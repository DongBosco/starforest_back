package com.project.starforest.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.project.starforest.domain.Diary;
import com.project.starforest.domain.DiaryImage;
import com.project.starforest.dto.DiaryDTO;
import com.project.starforest.dto.DiaryImageDTO;
import com.project.starforest.repository.DiaryRepository;
import com.project.starforest.service.DiaryImageService;
import com.project.starforest.service.DiaryService;
import com.project.starforest.service.S3FileUploadService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {
	
	private final DiaryRepository diaryRepository;
	
	private final DiaryImageService diaryImageService;
	private final S3FileUploadService s3FileUploadService;
	
	@Value("${app.image.base-url}")
	private String imageBaseUrl;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;
	
	
	// 별숲기록 생성 서비스
	@Override
	@Transactional
	public DiaryDTO createDiary(DiaryDTO diaryDTO, List<MultipartFile> images) {
		Diary diary = convertToEntity(diaryDTO);
		diary.changeCreated_at(LocalDateTime.now());
		Diary savedDiary = diaryRepository.save(diary);
		
		List<String> savedImageNames = new ArrayList<>();
		if (images != null && !images.isEmpty()) {
		    savedImageNames = diaryImageService.saveImages(savedDiary.getId(), images);
		}
		
		DiaryDTO savedDiaryDTO = convertToDTO(savedDiary);
		savedDiaryDTO.setImage_url(savedImageNames);
		
		
		return savedDiaryDTO;
	}
	
	
	
	
	

    
	
	// 모든 별숲기록 조회 서비스
	@Override
	@Transactional(readOnly = true)
	public List<DiaryDTO> getAllDiaries(Long lastId, int size) {
        Pageable pageable = PageRequest.of(0, size);
        Slice<Diary> diarySlice = diaryRepository.findDiariesForInfiniteScroll(lastId, pageable);
        
        List<DiaryDTO> diaryDTOs = diarySlice.getContent().stream()
                .map(this::convertToDTO)
                .peek(this::setImage_url)
                .collect(Collectors.toList());
        
        return diaryDTOs;
    }

	
	// 별숲기록 아이디로 view 조회 서비스
	@Override
	@Transactional
	public DiaryDTO getDiaryById(Long id) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지않는 별숲기록입니다."));
        DiaryDTO diaryDTO = convertToDTO(diary);
        
//        setImage_url(diaryDTO);
        List<String> imageUrls = diaryImageService.getImagesByDiaryId(diary.getId())
        		.stream()
        		.map(DiaryImageDTO::getImage_url)
        		.collect(Collectors.toList());
        diaryDTO.setImage_url(imageUrls);
        
        
        return diaryDTO;
	}
	
	// 한 유저가 쓴 별숲기록 조회 서비스
	@Override
	@Transactional(readOnly = true)
	public List<DiaryDTO> getDiariesByUser(String userEmail) {
        return diaryRepository.findAllByUserEmail(userEmail).stream()
                .map(this::convertToDTO)
                .peek(this::setImage_url)
                .collect(Collectors.toList());
	}
	
	
	// 별숲기록 삭제 서비스
	@Override
	@Transactional
	public void deleteDiary(Long id) {
	    Diary diary = diaryRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("존재하지않는 별숲기록입니다."));
	    
	    // 연관된 이미지 URL 가져오기
	    List<String> imageUrls = diaryImageService.getImagesForStringByDiaryId(id);
	    
	    // S3에서 이미지 파일 삭제
	    for (String imageUrl : imageUrls) {
	        String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
	        s3FileUploadService.deleteFile(fileName);
	    }
	    
	    // DiaryImage 레코드 삭제
	    diaryImageService.deleteImagesByDiaryId(id);
	    
	    // Diary 레코드 삭제
	    diaryRepository.delete(diary);
	}
	
	// 이미지 삭제 서비스
	@Override
	@Transactional
	public void deleteImages(List<String> imageUrls) {
        for (String imageUrl : imageUrls) {
            String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
            s3FileUploadService.deleteFile(fileName);
            diaryImageService.deleteImageByUrl(imageUrl);
        }
    }
	
	
	@Override
	@Transactional
	public void deleteImagesByUrl(String imageUrl) {
	    String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
	    s3FileUploadService.deleteFile(fileName);
	    diaryImageService.deleteImageByUrl(imageUrl);
	}
	
	
	
	

	
	
	// 이미지 저장 서비스
	@Override
	@Transactional
	public List<String> saveImages(List<MultipartFile> images) {
	    List<String> savedImageUrls = new ArrayList<>();
	    for (MultipartFile image : images) {
	        try {
	            String imageUrl = s3FileUploadService.uploadFile(image);
	            savedImageUrls.add(imageUrl);
	        } catch (IOException e) {
	            throw new RuntimeException("Failed to upload image", e);
	        }
	    }
	    return savedImageUrls;
	}
	
	 
	
	

	
	
	private Diary convertToEntity(DiaryDTO diaryDTO) {
		return Diary.builder()
				.content(diaryDTO.getContent())
				.category(diaryDTO.getAllTags())
				.build();
	}
	
	private DiaryDTO convertToDTO(Diary diary) {
	    DiaryDTO dto = DiaryDTO.builder()
	        .id(diary.getId())
	        .content(diary.getContent())
	        .allTags(diary.getCategory())
	        .created_at(diary.getCreated_at())
	        .build();

	    // Diary 엔티티에서 이미지 URL 리스트를 가져옵니다.
	    List<String> imageUrls = diaryImageService.getImagesForStringByDiaryId(diary.getId());

	    

	    return dto;
	}
	
	

	
	private void setImage_url(DiaryDTO diaryDTO) {
        List<String> imageNames = diaryImageService.getImagesByDiaryId(diaryDTO.getId())
                .stream()
                .map(DiaryImageDTO::getImage_url)
                .collect(Collectors.toList());
        diaryDTO.setImage_url(imageNames);
    }
	
	
	
	

	
	
}
