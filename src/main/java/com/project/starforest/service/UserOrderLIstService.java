package com.project.starforest.service;

import java.util.List;

import com.project.starforest.domain.Order;

public interface UserOrderLIstService {
	List<Order> getAllOrderList(String email);
}
