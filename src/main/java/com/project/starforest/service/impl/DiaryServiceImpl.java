package com.project.starforest.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.project.starforest.domain.Diary;
import com.project.starforest.dto.DiaryDTO;
import com.project.starforest.dto.DiaryImageDTO;
import com.project.starforest.repository.DiaryRepository;
import com.project.starforest.service.DiaryImageService;
import com.project.starforest.service.DiaryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {
	
	private final DiaryRepository diaryRepository;
	private final DiaryImageService diaryImageService;
	
	// 별숲기록 생성 서비스
	@Override
	@Transactional
	public DiaryDTO createDiary(DiaryDTO diaryDTO, List<MultipartFile> images) {
		Diary diary = convertToEntity(diaryDTO);
		diary.changeCreated_at(LocalDateTime.now());
		Diary savedDiary = diaryRepository.save(diary);
		
		List<String> savedImageUrls = diaryImageService.saveImages(savedDiary.getId(), images);
		DiaryDTO savedDiaryDTO = convertToDTO(savedDiary);
		savedDiaryDTO.setImage_url(savedImageUrls);
		
		return savedDiaryDTO;
	}
    
	
	// 모든 별숲기록 조회 서비스
	@Override
	@Transactional(readOnly = true)
	public List<DiaryDTO> getAllDiaries(Long lastId, int size) {
		List<Diary> diaries;
		if (lastId == null) {
			diaries = diaryRepository.findTopN(size);
		}else {
			diaries = diaryRepository.findNextN(lastId, size);
		}
		
		return diaries.stream()
				.map(this::convertToDTO)
				.peek(this::setImage_url)
				.collect(Collectors.toList());
		
		
//        return diaryRepository.findAll().stream()
//                .map(this::convertToDTO)
//                .peek(this::setImage_url)
//                .collect(Collectors.toList());
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
        diaryImageService.deleteImage(id);
        diaryRepository.deleteById(id);
	}
	
	
	
	private DiaryDTO convertToDTO(Diary diary) {
        DiaryDTO dto = DiaryDTO.builder()
                .id(diary.getId())
                .reservationId(diary.getReservation().getId())
                .userEmail(diary.getUser().getEmail())
                .content(diary.getContent())
                .allTags(diary.getCategory())
                .created_at(diary.getCreated_at())
                .build();
        
        List<String> imageUrls = diaryImageService.getImagesByDiaryId(diary.getId())
                .stream()
                .map(DiaryImageDTO::getImage_url)
                .collect(Collectors.toList());
        dto.setImage_url(imageUrls);
        
        return dto;
    }
	
	
	private Diary convertToEntity(DiaryDTO diaryDTO) {
        return Diary.builder()
                .id(diaryDTO.getId())
                .content(diaryDTO.getContent())
                .category(diaryDTO.getAllTags())
                .created_at(diaryDTO.getCreated_at())
                .build();
	}
	
	
	 private void setImage_url(DiaryDTO diaryDTO) {
	        List<String> imageUrls = diaryImageService.getImagesByDiaryId(diaryDTO.getId())
	                .stream()
	                .map(DiaryImageDTO::getImage_url)
	                .collect(Collectors.toList());
	        diaryDTO.setImage_url(imageUrls);
	    }

}
