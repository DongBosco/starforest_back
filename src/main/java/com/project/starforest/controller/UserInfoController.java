package com.project.starforest.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.starforest.domain.Order;
import com.project.starforest.dto.store.UserStoreOrderListDTO;
import com.project.starforest.service.UserOrderLIstService;


@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/user")
public class UserInfoController {
	@Autowired
	private UserOrderLIstService userOrderLIstService;
	
	
	
	
	
	
	//동일작업
	
	@GetMapping("/store/order/list/{email}")
	public ResponseEntity<List<UserStoreOrderListDTO>>  getAllOrderList(
			@PathVariable("email") String email
			) {
		
		List<UserStoreOrderListDTO> result = userOrderLIstService.getAllOrderList(email);
		log.info("resultresultresultresult"+result.toString());
		return ResponseEntity.ok(result);
	}
	
	//동일 작업
}