package com.project.starforest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.project.starforest.domain.CampSite;

@Repository
public interface MapTestRepository extends JpaRepository<CampSite, Long>{

}
