package com.project.starforest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.starforest.domain.Member;
import com.project.starforest.domain.Order;
import com.project.starforest.domain.Product;

public interface OrderkakaoRepository extends JpaRepository<Order, Long>{

	@Query("SELECT o FROM Order o WHERE o.member = :member")
	List<Order> findByEmail(@Param("member") Member member);

	@Query("SELECT o FROM Order o WHERE o.order_number = :orderId")
	Order findByOrderId(@Param("orderId") String orderId);

}
