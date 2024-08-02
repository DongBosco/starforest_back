package com.project.starforest.controller;


import com.project.starforest.domain.Reservation;
import com.project.starforest.dto.camp.CampListDTO;
import com.project.starforest.dto.userInfo.ResReservListDTO;
import com.project.starforest.dto.userInfo.ResReservViewDTO;
import com.project.starforest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/user")
public class UserInfoController {

    private final UserService userService;

    // -> user/camp/reservation/list => 해야할일? => 유저의 아이디로 예약을 조회해서, 뿌려줘야함. 이때, 캠핑장에 대한 정보도 같이 가져와야한다.
    @GetMapping("/camp/list")
    public List<ResReservListDTO> getMyReservList(@RequestParam(name="email") String email) throws Exception {
        try {
            List<ResReservListDTO> res = userService.getMyReservList(email);
            System.out.println(res);
            return res;
        }catch (Exception e){
            log.info("예약 조회중 오류 발생했습니다.");
            return null;
        }
    }

    @GetMapping("/camp/view")
    public ResponseEntity<?> getReservData(@RequestParam(name="reservationid") Long reservationid, @RequestParam(name="email") String email) {
        try {
            System.out.println("입력");
            ResReservViewDTO res = userService.getDetail(reservationid, email);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            log.info("예약 조회 중 오류 발생: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("예약 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}