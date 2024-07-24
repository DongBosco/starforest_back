package com.project.starforest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import com.project.starforest.domain.CampSite;
import com.project.starforest.repository.PointRepository;


@Service
public class CoordinatesService {

	@Autowired
	private PointRepository pointRepository;

	public List<CampSite> findNearbyPoints(CampSite mapEntity) {
		 return pointRepository.findNearbyPoints(mapEntity.getMapx(), mapEntity.getMapy(), 7);    }
}
