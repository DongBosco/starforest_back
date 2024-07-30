package com.project.starforest.repository;

import java.util.List;

import com.project.starforest.domain.CampSiteLike;
import com.project.starforest.domain.Member;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CampLikeRepository extends JpaRepository<CampSiteLike, Long> {
	@Query("SELECT c " +
            "FROM CampSiteLike c " +
            "WHERE c.member.email = :userEmail " +
            "AND c.camp_site_id.id = :campId")
    boolean existsByUserEmailAndCampSiteId(@Param("userEmail") String userEmail, @Param("campId") Long campId);

    @Query("DELETE FROM CampSiteLike c " +
            "WHERE c.member.email = :userEmail " +
            "AND c.camp_site_id.id = :campId")
    void deleteByUserEmailAndCampSiteId(@Param("userEmail") String userEmail, @Param("campId") Long campId);

    List<CampSiteLike> findByMember(Member member);
}