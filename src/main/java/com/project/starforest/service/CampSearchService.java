package com.project.starforest.service;

import com.project.starforest.dto.camp.CampSearchDTO;


import java.util.List;

public interface CampSearchService {

    List<CampSearchDTO> searchCamp(String query);

    String formatQuery(String query);
}
