package com.project.starforest.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.starforest.dto.DiaryDTO;

public interface DiaryService {
	DiaryDTO createDiary(DiaryDTO diaryDTO, List<MultipartFile> images);
    List<DiaryDTO> getAllDiaries(Long lastId, int size);
    DiaryDTO getDiaryById(Long id);
    List<DiaryDTO> getDiariesByUser(String userEmail);
    void deleteDiary(Long id);
}
