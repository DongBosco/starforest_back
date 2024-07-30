package com.project.starforest.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.starforest.domain.CampSite;
import com.project.starforest.domain.CampSiteLike;
import com.project.starforest.domain.Member;
import com.project.starforest.repository.CampLikeRepository;
import com.project.starforest.repository.CampSearchRepository;
import com.project.starforest.repository.MemberRepository;

@Service
public class CampLikeService {

    @Autowired
    private CampLikeRepository campLikeRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CampSearchRepository campRepository;

 // 모든 캠핑장 리스트를 반환하는 메서드
    public List<CampSite> getAllCamps() {
        return campRepository.findAll();
    }
    
    public boolean checkLikeStatus(String userEmail, Long campId) {
        return campLikeRepository.existsByUserEmailAndCampSiteId(userEmail, campId);
    }

    @Transactional
    public boolean toggleLike(String userEmail, Long campId) {
        if (campLikeRepository.existsByUserEmailAndCampSiteId(userEmail, campId)) {
            campLikeRepository.deleteByUserEmailAndCampSiteId(userEmail, campId);
            return false;
        } else {
            Member user = memberRepository.findById(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
            CampSite campSite = campRepository.findById(campId).orElseThrow(() -> new RuntimeException("CampSite not found"));
            CampSiteLike campSiteLike = CampSiteLike.builder()
                .user(user)
                .camp_site_id(campSite)
                .build();
            campLikeRepository.save(campSiteLike);
            return true;
        }
    }

    public List<CampSite> getLikedCamps(String userEmail) {
        Member user = memberRepository.findById(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        return campLikeRepository.findByUser(user).stream()
                .map(CampSiteLike::getCamp_site_id)
                .collect(Collectors.toList());
    }
    
    public List<Map<String, Object>> getAllCampsWithLikeStatus(String userEmail) {
        List<CampSite> allCamps = campRepository.findAll();
        return allCamps.stream().map(camp -> {
            Map<String, Object> campInfo = new HashMap<>();
            campInfo.put("camp", camp);
            campInfo.put("isLiked", checkLikeStatus(userEmail, camp.getId()));
            return campInfo;
        }).collect(Collectors.toList());
    }
}