package com.project.starforest.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.starforest.domain.Diary;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
	List<Diary> findAllByUserEmail(String userEmail);
	
	@Query(" SELECT d "
			+ " FROM Diary d "
			+ " WHERE d.id > :lastId "
			+ " ORDER BY d.id "
			+ " ASC LIMIT :size ")
	List<Diary> findNextN(@Param("lastId") Long lastId, @Param("size") int size);
	
	
	@Query(" SELECT d "
			+ " FROM Diary d "
			+ " ORDER BY d.id "
			+ " ASC LIMIT :size ")
	List<Diary> findTopN(@Param("size") int size);
	
	
	
}

