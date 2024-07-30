package com.project.starforest.repository;


import com.project.starforest.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<Product, Long> {

}
