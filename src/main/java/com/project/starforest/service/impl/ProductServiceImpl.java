package com.project.starforest.service.impl;

import java.util.List;
import java.util.Optional;

import com.project.starforest.domain.Product;
import com.project.starforest.domain.ProductReview;
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

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@Transactional
public class ProductServiceImpl implements ProductService {
	
	//ProductRepository인터페이스를 사용하여 db작업을 수행
	private final ProductRepository productRepository;
	
	
	//Autowired를 이용해서 자동으로 productRepository도구를 ProductServiceImpl클래스에 주입해줌
	@Autowired
	public  ProductServiceImpl(ProductRepository productRepository) {
		//받은 productRepository를 this(ProductServiceImpl안에 넣는 작업
		this.productRepository = productRepository;
	}
	
	//특정 제품의 ID를 이용해서 특정 제품을 찾아서 반환
	@Override
	public Product getProductById(Long productId) {
		//productRepository.findById(productId)를 호출해서 제품을 찾고 찾지못하면 메시지 던짐
		return productRepository.findById(productId)
				.orElseThrow();
	}
	
	//모든 제품의 목록을 반환
	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
//	//페이지별로 나누어진 제품 목록을 반환
//	@Override
//	public Page<Object[]> getProductList(Pageable pageable) {
//		return productRepository.selectList(pageable);  //페이지네이션을 적용한 제품목록을 가져옴
//	}
	
	
	//특정 제품에 대한 모든 리뷰를 가져옴
	@Autowired
	private ProductReviewRepository productReviewRepository;
	
	@Override
	public List<ProductReview> getReviewsByProductId(Long productId) {  
		Product product = getProductById(productId); //getProductById로 제품을 찾고
		
		if (product != null) {
			List<ProductReview> reviewList = productReviewRepository.findByProductId(productId);
			
			
			return reviewList;			//그에 대한 리뷰 목록을 반환
		}
		return null;
	}
	
	//리뷰추가
	@Override
	public ProductReview addReview(ProductReview review) {
		Product product = getProductById(review.getProduct().getId());  
		product.addReview(review);
		productRepository.save(product);
		return review;
	}
	
//	//특정제품삭제
//	@Override
//	public void deleteProduct (Long productId) { //단순삭에의 경우 void사용 무조건x / 성공,실패의 정보를 반환해야한다면 void사용x->boolean같은 데이터터입을 반환
//		productRepository.updateToDelete(productId, true);  //delflag를 이용하여 true로 설정하여 제품을 삭제 상태로표시
//	}
//	

//	@Override
//	public PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO) {
//		Pageable pageable = PageRequest.of(pageRequestDTO.getPage()-1,
//				pageRequestDTO.getSize(),
//				Sort.by("pno").descending());
//
//		Page<Object[]> result = productRepository.selectList(pageable);
//
//		//object[] -> 0 p 0 pimage
//		//object[] -> 1 p 0 pimage
//
////		List<ProductDTO> dtoList = result.get().map(null).toList();
//		List<ProductDTO> dtoList = result.get().map(
//				arr -> {
//					ProductDTO productDTO = null;
//					Product product = (Product) arr[0];
//					ProductImage productImage = (ProductImage) arr[1];
//
//					productDTO = ProductDTO.builder()
//							.pno(product.getPno())
//							.pname(product.getPname())
//							.pdesc(product.getPdesc())
//							.price(product.getPrice())
//							.build();
//
//					String imageStr = productImage.getFileName();
//					productDTO.setUploadFileNames(List.of(imageStr));
//
//					return productDTO;
//
//				})
//				.toList();
//
//		long totalCount = result.getTotalElements();
//
//		return PageResponseDTO.<ProductDTO>withAll()
//				.dtoList(dtoList)
//				.totalCount(totalCount)
//				.pageRequestDTO(pageRequestDTO)
//				.build();
//	}
//
//
//	@Override
//	public Long register(ProductDTO productDTO) {
//		Product product = dtoToEntity(productDTO);
//
//		log.info("#########################");
//		log.info(product);
//		log.info(product.getImageList());
//
//
//		Long pno = productRepository.save(product).getPno();
//
//		return pno;
//	}
//
//
//	private Product dtoToEntity(ProductDTO productDTO) {
//		Product product = Product.builder()
//				.pno(productDTO.getPno())
//				.pname(productDTO.getPname())
//				.pdesc(productDTO.getPdesc())
//				.price(productDTO.getPrice())
//				.build();
//
//		List<String> upLoadFileNames = productDTO.getUploadFileNames();
//
//		if(upLoadFileNames == null || upLoadFileNames.isEmpty()) {
//			return product;
//		}
//
//		upLoadFileNames.forEach(fileName -> {
//			product.addImageString(fileName);
//		});
//
//		return product;
//	}
//
//
//	@Override
//	public ProductDTO get(Long pno) {
//		Optional<Product> result = productRepository.findById(pno);
//		Product product = result.orElseThrow();
//
//		ProductDTO productDTO = entityToDto(product);
//
//		return productDTO;
//	}
//
//
//	private ProductDTO entityToDto(Product product) {
//
//		ProductDTO productDTO = ProductDTO.builder()
//				.pno(product.getPno())
//				.pname(product.getPname())
//				.pdesc(product.getPdesc())
//				.price(product.getPrice())
//				.delFlag(product.isDelFlag())
//				.build();
//
//		List<ProductImage> imageList = product.getImageList();
//
//		if(imageList == null || imageList.size()==0) {
//			return productDTO;
//		}
//
//		List<String> fileNameList = imageList.stream()
//				.map(productImage -> productImage.getFileName())
//				.toList();
//
//		productDTO.setUploadFileNames(fileNameList);
//
//		return productDTO;
//	}
//
//
//	@Override
//	public void modify(ProductDTO productDTO) {
//
//		//#1 pno read
//		Optional<Product> result = productRepository.findById(productDTO.getPno());
//		Product product = result.orElseThrow();
//
//		//#2 change
//		product.changePname(productDTO.getPname());
//		product.changePrice(productDTO.getPrice());
//		product.changePdesc(productDTO.getPdesc());
//
//		//#3 upload file clear
//		product.clearList();
//
//		List<String> uploadFileNames = productDTO.getUploadFileNames();
//
//		if(uploadFileNames != null && uploadFileNames.size() > 0) {
//			uploadFileNames.stream().forEach(
//					uploadName -> {
//						product.addImageString(uploadName);
//					});
//		}
//
//		//last
//		productRepository.save(product);
//
//
//	}
//
//
//	@Override
//	public void remove(Long pno) {
//		// TODO Auto-generated method stub

	}








