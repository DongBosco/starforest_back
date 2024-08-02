package com.project.starforest.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.project.starforest.domain.Product;
import com.project.starforest.domain.ProductImage;
import com.project.starforest.domain.ProductReview;
import com.project.starforest.dto.store.ProductDTO;
import com.project.starforest.dto.store.ProductImagesDTO;
import com.project.starforest.dto.store.ProductResponseDTO;
import com.project.starforest.dto.store.ProductReviewDTO;
import com.project.starforest.repository.ProductImageRepository;
import com.project.starforest.repository.ProductRepository;
import com.project.starforest.repository.ProductReviewRepository;
import com.project.starforest.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	//ProductRepository인터페이스를 사용하여 db작업을 수행
	private final ProductRepository productRepository;
	private final ProductReviewRepository productReviewRepository;
	private final ProductImageRepository productImageRepository;

//	//Autowired를 이용해서 자동으로 productRepository도구를 ProductServiceImpl클래스에 주입해줌
//	@Autowired
//	public  ProductServiceImpl(ProductRepository productRepository, ProductReviewRepository productReviewRepository) {
//		//받은 productRepository를 this(ProductServiceImpl안에 넣는 작업
//		this.productRepository = productRepository;
//		this.productReviewRepository = productReviewRepository;
//	}
	
	//특정 제품의 ID를 이용해서 특정 제품을 찾아서 반환
	@Override
	public ProductDTO getProductById(Long productId) {
		//productRepository.findById(productId)를 호출해서 제품을 찾고 찾지못하면 메시지 던짐
		Product productEntity = productRepository.findById(productId).orElseThrow();
		List<String> productImageEntity = productImageRepository.findByProductId(productId);
		log.info("productEntityproductEntityproductEntityproductEntityproductEntity" + productEntity.toString());
		log.info("productImageEntityproductImageEntityproductImageEntity" + productImageEntity.toString());
		ProductDTO productDTO = ProductDTO.builder()
				.productId(productEntity.getId())
				.imageList(productImageEntity)
				.productName(productEntity.getProduct_name())
				.brandName(productEntity.getBrand_name())
				.type(productEntity.getType())
				.price(productEntity.getPrice())
				.build();
				log.info("productDTOproductDTOproductDTO" + productDTO);
		return productDTO;
		
	}
		
//	모든 제품의 목록을 반환
	@Override
	public List<ProductDTO> getAllProducts() {
		//productRepository를 이용해서  모든 productEntity를 가져온다
		List<Product> productEntity =  productRepository.findAll();
		//제품리스트를 stream으로 변환
		  List<ProductDTO> productDTOs = productEntity.stream()
				  	//builder/build를 이용해서 entity를 DTO형식으로 변환
		            .map(entity -> ProductDTO.builder()
		                .productId(entity.getId())
		                .productName(entity.getProduct_name())
		                .brandName(entity.getBrand_name())
		                .type(entity.getType())
		                .price(entity.getPrice())
		                .first_img_url(entity.getImgUrls())
		                .build())
//		            //변환된 productDTO의객체들을 리스트로 수집
		            .collect(Collectors.toList());
		            
		return productDTOs;
	}
	
	
	
	
	
//	public List<ProductDTO> getProductsByType(int type) {
//		List<ProductDTO> allProducts = getAllProducts();
//					//stream을 이용해서 전체목록에서 특정 타입의 제품만 필터링
//					//int형일땐 equals대신 == 연산자로 비교 / string일땐 equals
//		return allProducts.stream().filter(product -> product.getType() ==type).collect(Collectors.toList());	
//	}
//	//builder사용
	@Override
	public List<ProductDTO> getProductsByType(int type) {
	    List<ProductDTO> allProducts = getAllProducts();
	    
	    if (type==3) {
	    	return allProducts;
	    }
	    
	    return allProducts.stream()
	            .filter(product -> product.getType() == type) // 타입으로 필터링
	            .map(product -> ProductDTO.builder()
	                    .productId(product.getProductId())
	                    .productName(product.getProductName())
	                    .brandName(product.getBrandName())
	                    .type(product.getType())
	                    .price(product.getPrice())
	                    .first_img_url(product.getFirst_img_url())
	                    .imageList(product.getImageList())
	                    .productReview(product.getProductReview())
	                    .images(product.getImages())
	                    .build()) // 빌더 패턴으로 DTO 변환
	            .collect(Collectors.toList()); // 리스트로 수집
	}
	
	
	
    // 특정 제품에 대한 모든 리뷰를 가져옴
    @Override
    public List<ProductReview> getReviewsByProductId(Long productId) {  
        ProductDTO product = getProductById(productId);
        if (product != null) {
            return productReviewRepository.findByProductId(productId);
        }
        return null;
    }
    
    //특정상품의 리뷰추가 ??????????????????????????????????
    public ProductReview addReview(ProductReviewDTO reviewDTO) {
        ProductDTO product = getProductById(reviewDTO.getProductId());
        //프론트에서 member id를 찾고 member가 있으면 결과값을 builder에 넣기
        ProductReview review = ProductReview.builder()
//        		.product(product)
        		.content(reviewDTO.getContent())
        		.created_at(LocalDateTime.now())
        		.build();
        
        
        return productReviewRepository.save(review);
    }
    
    //특정제품의 모든 이미지 가져오기
//    @Override
//    public List<ProductImagesDTO> getImagesByProductId(Long productId) {
//    		ProductDTO product =  getProductById(productId);
//    		return product.getImageList().stream()
//    				.map(image -> ProductImagesDTO.builder()
//    						.id(image.getId())
////    						.imageIndex(image.getImage_index())
////    						.imageUrl(image.getImage_url())
//    						.build())
//    				.collect(Collectors.toList());
//    }

//	@Override
//	public ShoppingCartItem addItemTocart(ShoppingCartItem item) {
//
//		return null;
//	}
//
	
//	private ProductDTO convertToDTO(Product product) {
//        return ProductDTO.builder()
//                .productId(product.getId())
//                .productName(product.getProduct_name())
//                .brandName(product.getBrand_name())
//                .type(product.getType())
//                .price(product.getPrice())
//                .firstImgUrl(product.get())
//                .imageList(product.getImageList().stream()
//                        .map(image -> new ProductImagesDTO(image.getId(), image.getImageIndex(), image.getImageUrl()))
//                        .collect(Collectors.toList()))
//                .productReview(product.getProductReviews().stream()
//                        .map(review -> new ProductReviewDTO(review.getId(), review.getContent(), review.getCreatedAt()))
//                        .collect(Collectors.toList()))
//                .starsale(product.getStarsale())
//                .delivery(product.getDelivery())
//                .build();
//    }
    
    
    
    //동일 작성
    public ProductResponseDTO getProductByIdOfEntity(Long productId) {
    	
    	Product result = productRepository.findById(productId).orElseThrow();
    	ProductResponseDTO resultDto = ProductResponseDTO.builder()
    			.id(result.getId())
    			.imgUrls(result.getImgUrls())
    			.product_name(result.getProduct_name())
    			.brand_name(result.getBrand_name())
    			.price(result.getPrice())
    			.type(result.getType())
    			.build();
    	
    	return resultDto;
    }
    //동일 작성
    
}






