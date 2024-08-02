package com.project.starforest.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.starforest.domain.Member;
import com.project.starforest.domain.Order;
import com.project.starforest.domain.OrderInfo;
import com.project.starforest.domain.Product;
import com.project.starforest.dto.store.UserStoreOrderListDTO;
import com.project.starforest.repository.MemberRepository;
import com.project.starforest.repository.OrderInfoKakaoRepository;
import com.project.starforest.repository.OrderkakaoRepository;
import com.project.starforest.service.UserOrderLIstService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserOrderLIstServiceImpl implements UserOrderLIstService{

	@Autowired
	private OrderkakaoRepository orderkakaoRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private OrderInfoKakaoRepository orderInfoKakaoRepository;
	
	public List<UserStoreOrderListDTO> getAllOrderList(String email) {
		
		Member member = memberRepository.findByEmail(email);
		log.info("membermembermembermembermember"+member.toString());
		
		List<Order> orderList = orderkakaoRepository.findByEmail(member);
		log.info("OrderListOrderListOrderListOrderList"+orderList.toString());
	
	    List<UserStoreOrderListDTO> dtoList = orderList.stream()
	            .map(order -> {
	                Product product = order.getProduct();
	                Long orderId = order.getId();
	                OrderInfo orderInfo = orderInfoKakaoRepository.findByOrderId(orderId);
	                
	                return UserStoreOrderListDTO.builder()
	                	.totalPrice(orderInfo != null && orderInfo.getTotal_price() != null ? orderInfo.getTotal_price() : 0)
	                	.imageUrl(order.getProduct().getImgUrls())
	                    .orderId(order.getId())
	                    .orderNum(order.getOrder_number())
	                    .productId(product != null ? product.getId() : null)
	                    .price(product != null ? product.getPrice() : null)
	                    .productName(product != null ? product.getProduct_name() : null)
	                    .brandName(product != null ? product.getBrand_name() : null)
	                    .created_at(order.getCreated_at())
	                    .build();
	            })
	            .collect(Collectors.toList());
				
		
		return dtoList;
	}
	
}
