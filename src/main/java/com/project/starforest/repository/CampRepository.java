package com.project.starforest.repository;

import java.util.List;

import com.project.starforest.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.project.starforest.domain.CampSite;
import org.springframework.stereotype.Repository;


@Repository
public interface CampRepository extends JpaRepository<CampSite, Long> {
    @Query("SELECT c " +
            "FROM CampSite c " +
            "WHERE c.id = :id")
    CampSite findByCampId(@Param("id")Long id);

//    @Query("SELECT m from CampSite m WHERE m.name LIKE :name")
//    List<CampSite> findByNameContained(@Param("name") String name);
}
