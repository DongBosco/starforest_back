package com.project.starforest.service;


import com.project.starforest.domain.CampSite;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
public interface CampLikeService {

    // 모든 캠핑장 리스트를 반환하는 메서드
    List<CampSite> getAllCamps();

    boolean checkLikeStatus(String userEmail, Long campId);


    boolean toggleLike(String userEmail, Long campId);

    List<CampSite> getLikedCamps(String userEmail) ;

    List<Map<String, Object>> getAllCampsWithLikeStatus(String userEmail);
}