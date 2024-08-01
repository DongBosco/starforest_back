package com.project.starforest.service.impl;

import com.project.starforest.domain.CampSite;
import com.project.starforest.domain.ReservInfo;
import com.project.starforest.domain.Reservation;
import com.project.starforest.dto.camp.CampListDTO;
import com.project.starforest.dto.userInfo.ResReservListDTO;
import com.project.starforest.repository.*;
import com.project.starforest.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class UserServiceImpl implements UserService {
    private static final String GET_RESERV_ERROR = "예약 조회중 오류가 발생하였습니다.";

    //=================================================================================
    private final CampRepository campRepository;
    private final ReservationRepository reservDateRepository;
    private final ReservationedRepository reservationRepository;
    private final ReservInfoRepository reservInfoRepository;




    public UserServiceImpl(CampRepository campRepository, UserInfoRepository userInfoRepository, MemberRepository memberRepository, ReservationRepository reservDateRepository, ReservationRepository reservationRepository, ReservationRepository reservDateRepository1, ReservationedRepository reservationRepository1, ReservInfoRepository reservInfoRepository) {
        this.campRepository = campRepository;
        this.reservDateRepository = reservDateRepository1;
        this.reservationRepository = reservationRepository1;
        this.reservInfoRepository = reservInfoRepository;
    }


    @Override
    public List<CampListDTO> getSite(Pageable pageable) {
        Page<CampSite> campResult =  campRepository.findAll(pageable);

        List<CampListDTO> campList = campResult.stream().map(dto->CampListDTO.builder()
                        .id(dto.getId())
                        .name(dto.getName())
                        .is_glamp(dto.is_glamp())
                        .is_auto(dto.is_auto())
                        .is_carvan(dto.is_carvan())
                        .add1(dto.getAdd1())
                        .price(dto.getPrice())
                        .first_image_url(dto.getFirst_image_url())
                        .thema_envrn_cl(dto.getThema_envrn_cl())
                        .build())
                .toList();

        return campList;
    }

//    private Long id;
//    private LocalDateTime create_at;
//    private LocalDateTime start_date;
//    private LocalDateTime end_date;
//    private String reservation_number;
//    private boolean isRecordWritten;
//    private CampSite camp;
//    private ReservInfo info;

    @Override
    public List<ResReservListDTO> getMyReservList(String myEmail) throws Exception{
        List<ResReservListDTO> res= null;
        try {
            List<Reservation> myReserv = reservationRepository.findAllByEmail(myEmail);
            res = myReserv.stream()
                    .map(reservation ->
                            ResReservListDTO.builder()
                                    .id(reservation.getId())
                                    .camp(reservation.getCampsite_id())
                                    .info(reservInfoRepository.getReferenceByReserv(reservation))
                                    .reservation_number(reservation.getReservation_number())
                                    .start_date(reservation.getStart_date())
                                    .end_date(reservation.getEnd_date())
                                    .create_at(reservation.getCreated_at())
                                    .isRecordWritten(reservation.is_payment())
                                    .build())
                    .collect(Collectors.toList());
        }catch (Exception e){
            log.error(GET_RESERV_ERROR, e);
            throw e;
        }
        System.out.println(res);
        return res;
    }
}
