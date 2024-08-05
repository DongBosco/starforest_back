package com.project.starforest.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.starforest.domain.Member;
import com.project.starforest.domain.Product;
import com.project.starforest.domain.ProductReview;
import com.project.starforest.dto.store.UserOrderResponseDTO;
import com.project.starforest.dto.store.UserOrderReviewRequestDTO;
import com.project.starforest.dto.store.UserReviewListResponseDTO;
import com.project.starforest.repository.MemberRepository;
import com.project.starforest.repository.ProductRepository;
import com.project.starforest.repository.ProductReviewRepository;
import com.project.starforest.service.UserReviewService;

@Service
public class UserReviewServiceImpl implements UserReviewService{

	private ProductRepository productRepository;

	private ProductReviewRepository productReviewRepository;

	private MemberRepository memberRepository;



	public UserOrderResponseDTO getUserProduct(Long productId) {

		Product productEntity = productRepository.findById(productId).orElseThrow();

		UserOrderResponseDTO userOrderDTO = UserOrderResponseDTO.builder()
				.brandName(productEntity.getBrand_name())
				.productName(productEntity.getProduct_name())
				.imgUrl(productEntity.getImgUrls())
				.build();
		return userOrderDTO;
	}


	public String userReviewSave(UserOrderReviewRequestDTO userOrderDTO) {

		Long productId =userOrderDTO.getProductId();
		String memberEmail = userOrderDTO.getUserEmail();

		Product product = productRepository.findById(productId).orElseThrow();
		Member member = memberRepository.findByEmail(memberEmail);

		ProductReview reviewEntity = ProductReview.builder()
				.content(userOrderDTO.getContent())
				.product(product)
				.member(member)
				.created_at(LocalDateTime.now())
				.build();
		productReviewRepository.save(reviewEntity);
		return null;
	}

	public List<UserReviewListResponseDTO> userReviewList(String email) {

		List<ProductReview> userReviewList = productReviewRepository.findByEmail(email);

		List<UserReviewListResponseDTO> userReviewResponseDTO = userReviewList.stream()
				.map(entity -> UserReviewListResponseDTO.builder()
						.content(entity.getContent())
						.productName(entity.getProduct().getProduct_name())
						.brandName(entity.getProduct().getBrand_name())
						.imgUrl(entity.getProduct().getImgUrls())
						.build())
				.collect(Collectors.toList());

		return userReviewResponseDTO;
	}
}
