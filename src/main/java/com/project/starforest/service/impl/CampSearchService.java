package com.project.starforest.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.starforest.domain.CampSite;
import com.project.starforest.dto.CampSearchDTO;
import com.project.starforest.repository.CampSearchRepository;



@Service
public class CampSearchService {

	@Autowired
	private CampSearchRepository campSearchRepository;
	
	public List<CampSearchDTO> searchCamp(String query){
		
		//단어 가공 (%추가)
		String formattedQuery = formatQuery(query);
		
		List<CampSite> entities = campSearchRepository.findByNameContained(formattedQuery);
		
		List<CampSearchDTO> result = entities.stream().map(entity->CampSearchDTO.builder()
				.id(entity.getId())
				.name(entity.getName())
				.first_image_url(entity.getFirst_image_url())
				.is_auto(entity.is_auto())
				.is_carvan(entity.is_carvan())
				.is_glamp(entity.is_glamp())
				.price(entity.getPrice())
				.sigungu_nm(entity.getSigungu_nm())
				.build())
			.collect(Collectors.toList());
		
		return result;
	}
	
	//단어 사이사이에 % 추가
	private String formatQuery(String query) {
		 return "%" + String.join("%", query.split("")) + "%";
    }
}
