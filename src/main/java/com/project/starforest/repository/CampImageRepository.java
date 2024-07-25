package com.project.starforest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.starforest.domain.CampImage;

public interface CampImageRepository extends JpaRepository<CampImage, Long>{

	@Query("SELECT ci FROM CampImage ci WHERE ci.camp_site_id.id = :id")
	List<CampImage> findByCampId(@Param("id") Long id);

}
