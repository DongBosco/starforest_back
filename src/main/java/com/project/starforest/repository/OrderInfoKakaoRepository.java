package com.project.starforest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.starforest.domain.OrderInfo;

public interface OrderInfoKakaoRepository extends JpaRepository<OrderInfo, Long>{

}
