package com.project.starforest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.starforest.domain.CampSite;

public interface PointRepository extends JpaRepository<CampSite, Long>{

	  @Query(value = "SELECT * FROM camp_site WHERE ST_Distance_Sphere(location, POINT( :mapX, :mapY)) <= :radius * 1000", nativeQuery = true)
	    List<CampSite> findNearbyPoints(@Param("mapX") double mapX, @Param("mapY") double mapY, @Param("radius") double radius);
	
	  
	  //@Query(value = "SELECT p FROM MapEntity p ")
	    //List<MapEntity> findNearbyPoints(@Param("mapX") double mapX, @Param("mapY") double mapY, @Param("radius") double radius);
	  
	  
//	 @Query(value = "SELECT * FROM maptest p WHERE ST_Distance_Sphere(p.location, ST_GeomFromText(CONCAT('POINT(', :mapX, ' ', :mapY, ')'), 4326)) <= :radius * 1000", nativeQuery = true)
//	    List<MapEntity> findNearbyPoints(@Param("mapX") double mapX, @Param("mapY") double mapY, @Param("radius") double radius);
}
