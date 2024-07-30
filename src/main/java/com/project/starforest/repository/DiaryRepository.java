package com.project.starforest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.starforest.domain.Diary;

public interface DiaryRepository extends JpaRepository<Diary, Long>{

}
