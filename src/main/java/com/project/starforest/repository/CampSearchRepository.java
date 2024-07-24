package com.project.starforest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.starforest.domain.CampSite;


public interface CampSearchRepository extends JpaRepository<CampSite, Long>{
	
	@Query("SELECT m from CampSite m WHERE m.name LIKE :name")
	List<CampSite> findByNameContained(@Param("name") String name);
}
