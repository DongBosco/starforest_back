package com.project.starforest.service;
import jakarta.transaction.Transactional;

@Transactional
public interface ProductService {
	PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO);
	
	Long register(ProductDTO producDTO);
	
	ProductDTO get(Long pno);
	
	void modify(ProductDTO productDTO);
	
	void remove(Long pno);
}
