package com.project.starforest.service;

import java.util.List;

import com.project.starforest.domain.Order;
import com.project.starforest.dto.store.UserStoreOrderListDTO;

public interface UserOrderLIstService {
	List<UserStoreOrderListDTO> getAllOrderList(String email);
}
