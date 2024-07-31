package com.project.starforest.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.starforest.domain.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

	@Query("SELECT pi.image_url FROM ProductImage pi WHERE pi.product.id = :productId")
	 List<String> findByProductId(@Param("productId") Long productId);

}
