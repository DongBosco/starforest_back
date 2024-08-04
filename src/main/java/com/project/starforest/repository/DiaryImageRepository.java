package com.project.starforest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.starforest.domain.DiaryImage;

@Repository
public interface DiaryImageRepository extends JpaRepository<DiaryImage, Long> {
	List<DiaryImage> findByDiaryId(Long diaryId);
	void deleteByDiaryId(Long diaryId);
	
	@Modifying
    @Query("DELETE FROM DiaryImage d WHERE d.image_url = :imageUrl")
    void deleteByImageUrl(String imageUrl);
}
