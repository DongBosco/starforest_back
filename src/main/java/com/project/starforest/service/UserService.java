package com.project.starforest.service;

import com.project.starforest.dto.camp.CampListDTO;
import com.project.starforest.dto.userInfo.ResReservListDTO;
import com.project.starforest.dto.userInfo.ResReservViewDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Transactional
public interface UserService {

    List<CampListDTO> getSite(Pageable pageable);

    List<ResReservListDTO> getMyReservList (String myEmail) throws Exception;

    ResReservViewDTO getDetail (Long reservationId, String email) throws Exception;

}
