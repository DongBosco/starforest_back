package com.project.starforest.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.starforest.domain.Diary;
import com.project.starforest.repository.DiaryRepository;


@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/main")
public class MainController {

	@Autowired
	private DiaryRepository diaryRepository;
	@GetMapping("/diary/list")
	public String getAllDiaryList() {
		
		List<Diary> diaryEntitys = diaryRepository.findAll();
		
		return null;
	}
	
}