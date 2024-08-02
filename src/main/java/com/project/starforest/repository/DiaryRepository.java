package com.project.starforest.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import com.project.starforest.domain.Diary;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
	List<Diary> findAllByUserEmail(String userEmail);

	@Query("SELECT d " +
			"FROM Diary d " +
			"WHERE d.reservation.id = :reservationId")
	Diary findDiaryByReservationId(@Param("reservationId") Long reservationId);
	
//	@Query(" SELECT d "
//			+ " FROM Diary d "
//			+ " WHERE d.id > :lastId "
//			+ " ORDER BY d.id "
//			+ " ASC LIMIT :size ")
//	List<Diary> findNextN(@Param("lastId") Long lastId, @Param("size") int size);
	
	
//	@Query(" SELECT d "
//			+ " FROM Diary d "
//			+ " ORDER BY d.id "
//			+ " ASC LIMIT :size ")
//	List<Diary> findTopN(@Param("size") int size);
	
	
//	@Query("SELECT d FROM Diary d WHERE d.id > :lastId ORDER BY d.id ASC")
//    Page<Diary> findNextN(@Param("lastId") Long lastId, Pageable pageable);
//	
//	
//	@Query("SELECT d FROM Diary d ORDER BY d.id ASC")
//    Page<Diary> findTopN(Pageable pageable);
	
	@Query("SELECT d FROM Diary d WHERE (:lastId IS NULL OR d.id < :lastId) ORDER BY d.id DESC")
    Slice<Diary> findDiariesForInfiniteScroll(@Param("lastId") Long lastId, Pageable pageable);
	
	
}
