package com.project.starforest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.starforest.domain.Member;
import com.project.starforest.domain.Order;
import com.project.starforest.repository.MemberRepository;
import com.project.starforest.repository.OrderkakaoRepository;
import com.project.starforest.service.UserOrderLIstService;

@Service
public class UserOrderLIstServiceImpl implements UserOrderLIstService{

	@Autowired
	private OrderkakaoRepository orderkakaoRepository;
	
	@Autowired
	private MemberRepository memberRepository; 
	
	public List<Order> getAllOrderList(String email) {
		
		Member member = memberRepository.findByEmail(email);
		
		List<Order> OrderList = orderkakaoRepository.findByEmail(member);
		
		return OrderList;
	}
	
}
