package com.project.starforest.service.impl;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.starforest.domain.Member;
import com.project.starforest.domain.Order;
import com.project.starforest.domain.OrderInfo;
import com.project.starforest.domain.Product;
import com.project.starforest.dto.store.KakaoSuccessRequestDTO;
import com.project.starforest.repository.MemberRepository;
import com.project.starforest.repository.OrderInfoKakaoRepository;
import com.project.starforest.repository.OrderkakaoRepository;
import com.project.starforest.repository.ProductRepository;
import com.project.starforest.service.OrderKakaoService;

@Service
public class OrderkakaoServiceImpl implements OrderKakaoService{

	@Autowired
	private OrderkakaoRepository orderkakaoRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private OrderInfoKakaoRepository orderInfoKakaoRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public String OrderKakaoSave(KakaoSuccessRequestDTO dto) {
		
		String email = dto.getEmail();
		Long productId = dto.getProductId();
		
		Member member = memberRepository.findByEmail(email);
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new NoSuchElementException("Order not found with id: " + productId));

		
		Order orderEntity =Order.builder()
				.order_number(dto.getOrderId())
				.order_type(0)
				.created_at(LocalDateTime.now())
				.is_payment(true)
				.member(member)
				.product(product)
				.build(); 
		
		orderkakaoRepository.save(orderEntity);
		
		return null;
	}

	@Override
	public void OrderInfoKakaoSave(KakaoSuccessRequestDTO dto) {
		
		String orderId = dto.getOrderId();
		Order order= orderkakaoRepository.findByOrderId(orderId);
		
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
