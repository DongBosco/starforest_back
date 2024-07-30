package com.project.starforest.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.starforest.domain.CampSite;
import com.project.starforest.service.CampLikeService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/camps")
@Log4j2
public class CampLikeController {
	@Autowired
    private CampLikeService campLikeService;
 // 모든 캠핑장 리스트를 반환하는 엔드포인트
    @GetMapping("/list")
    public ResponseEntity<List<CampSite>> getAllCamps() {
        List<CampSite> allCamps = campLikeService.getAllCamps();
        return ResponseEntity.ok(allCamps);
    }
    
 // 사용자가 좋아요를 누른 캠핑장 리스트를 반환하는 엔드포인트
    @GetMapping("/liked")
    public ResponseEntity<List<CampSite>> getLikedCamps(@RequestAttribute String userEmail) {
        List<CampSite> likedCamps = campLikeService.getLikedCamps(userEmail);
        return ResponseEntity.ok(likedCamps);
    }
    
    @GetMapping("/check/{campId}")
    public ResponseEntity<?> checkLikeStatus(@PathVariable Long campId, @RequestAttribute String userEmail) {
        boolean isLiked = campLikeService.checkLikeStatus(userEmail, campId);
        return ResponseEntity.ok(new HashMap<String, Boolean>() {{
            put("isLiked", isLiked);
        }});
    }

    @PostMapping("/toggle/{campId}")
    public ResponseEntity<?> toggleLike(@PathVariable("campId") Long campId, @RequestAttribute String userEmail) {
        boolean isLiked = campLikeService.toggleLike(userEmail, campId);
        String message = isLiked ? "좋아요가 추가되었습니다." : "좋아요가 취소되었습니다.";
        return ResponseEntity.ok(new HashMap<String, Object>() {{
            put("isLiked", isLiked);
            put("message", message);
        }});
    }

    @GetMapping("/status/list")
    public ResponseEntity<List<Map<String, Object>>> getAllCampsWithLikeStatus(@RequestAttribute String userEmail) {
        List<Map<String, Object>> campsWithLikeStatus = campLikeService.getAllCampsWithLikeStatus(userEmail);
        return ResponseEntity.ok(campsWithLikeStatus);
    }
}