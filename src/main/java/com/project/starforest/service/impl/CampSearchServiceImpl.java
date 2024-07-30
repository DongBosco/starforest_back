package com.project.starforest.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.project.starforest.service.CampSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.starforest.domain.CampSite;
import com.project.starforest.dto.camp.CampSearchDTO;
import com.project.starforest.repository.CampSearchRepository;



@Service
public class CampSearchServiceImpl implements CampSearchService {

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
				.add1(entity.getAdd1())
				.thema_envrn_cl(entity.getThema_envrn_cl())
				.build())
			.collect(Collectors.toList());

		return result;
	}

	//단어 사이사이에 % 추가
	public String formatQuery(String query) {
		 return "%" + String.join("%", query.split("")) + "%";
    }
}
