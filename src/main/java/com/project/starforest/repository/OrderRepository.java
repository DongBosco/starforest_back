package com.project.starforest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.starforest.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    boolean existsByIdAndMemberEmail(Long id, String memberEmail);
}
