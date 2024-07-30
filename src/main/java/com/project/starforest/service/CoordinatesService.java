package com.project.starforest.service;

import com.project.starforest.domain.CampSite;

import java.util.List;

public interface CoordinatesService {

    public List<CampSite> findNearbyPoints(CampSite mapEntity);
}

