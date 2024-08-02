package com.project.starforest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.starforest.domain.OrderInfo;

public interface OrderInfoKakaoRepository extends JpaRepository<OrderInfo, Long>{

	@Query("SELECT oi FROM OrderInfo oi WHERE oi.order.id = :orderId")
	OrderInfo findByOrderId(@Param("orderId") Long orderId);

}
