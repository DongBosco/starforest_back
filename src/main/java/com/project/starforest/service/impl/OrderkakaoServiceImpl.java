package com.project.starforest.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.starforest.domain.Member;
import com.project.starforest.domain.Order;
import com.project.starforest.domain.OrderInfo;
import com.project.starforest.dto.store.KakaoSuccessRequestDTO;
import com.project.starforest.repository.MemberRepository;
import com.project.starforest.repository.OrderInfoKakaoRepository;
import com.project.starforest.repository.OrderkakaoRepository;
import com.project.starforest.service.OrderKakaoService;

@Service
public class OrderkakaoServiceImpl implements OrderKakaoService{

	@Autowired
	private OrderkakaoRepository orderkakaoRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private OrderInfoKakaoRepository orderInfoKakaoRepository;
	
	public String OrderKakaoSave(KakaoSuccessRequestDTO dto) {
		
		String email = dto.getEmail();
		Member member = memberRepository.findByEmail(email);
		
		Order orderEntity =Order.builder()
				.order_number(dto.getOrderId())
				.order_type(0)
				.created_at(LocalDateTime.now())
				.is_payment(true)
				.member(member)
				.build(); 
		
		orderkakaoRepository.save(orderEntity);
		
		return null;
	}

	@Override
	public void OrderInfoKakaoSave(KakaoSuccessRequestDTO dto) {
		
		Long productId = dto.getProductId();
		Order order= orderkakaoRepository.findById(productId).orElseThrow();
		
		OrderInfo orderInfoEntity = OrderInfo.builder()
				.name(dto.getName())
				.tel(dto.getTel())
				.total_price(dto.getTotalPrice())
				.address1(dto.getAdd())
				.address2(dto.getAddDetail())
				.order(order)
				.build();
		
		orderInfoKakaoRepository.save(orderInfoEntity);
		
	}
	
}
