package com.project.starforest.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.starforest.domain.Reservation;
import com.project.starforest.service.UserReservationService;

import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/user")
public class UserInfoController {
	
	@Autowired
	private UserReservationService usrReservationService;
	
	@GetMapping("/camp/list")
	public ResponseEntity<List<Reservation>> getMethodName(
			@RequestParam("page") Long page,
			@RequestParam("size") Long size,
			@RequestParam("email") String email
			) {
		
		List<Reservation> reservationList = usrReservationService.getMyAllReservationList(email);
		
		return ResponseEntity.ok(reservationList);
	}
	
	
}