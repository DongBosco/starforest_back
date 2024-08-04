package com.project.starforest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.starforest.domain.ProductReview;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long>{

//	@Query("SELECT pr FROM ProductReview pr WHERE pr.product.id = productId")
	 List<ProductReview> findByProductId(@Param("productId") Long productId);

	 
	 @Query("SELECT pr FROM ProductReview pr WHERE pr.member.email = :email")
	List<ProductReview> findByEmail(@Param("email") String email);

}