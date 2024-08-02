package com.project.starforest.service.impl;

import com.project.starforest.domain.*;
import com.project.starforest.dto.camp.CampListDTO;
import com.project.starforest.dto.userInfo.ResReservListDTO;
import com.project.starforest.dto.userInfo.ResReservViewDTO;
import com.project.starforest.repository.*;
import com.project.starforest.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class UserServiceImpl implements UserService {
    private static final String GET_RESERV_ERROR = "예약 조회중 오류가 발생하였습니다.";
    private static final String EMPTY_RESERVATION_ERROR ="예약 내역이 존재하지 않습니다.";
    private static final String NOT_UR_RESERVATION = "잘못된 요청입니다.";
    //=================================================================================
    private final CampRepository campRepository;
    private final ReservationRepository reservDateRepository;
    private final ReservationedRepository reservationRepository;
    private final ReservInfoRepository reservInfoRepository;
    private final DiaryRepository diaryRepository;

    public UserServiceImpl(CampRepository campRepository, UserInfoRepository userInfoRepository, MemberRepository memberRepository, ReservationRepository reservDateRepository, ReservationRepository reservationRepository, ReservationRepository reservDateRepository1, ReservationedRepository reservationRepository1, ReservInfoRepository reservInfoRepository, DiaryRepository diaryRepository) {
        this.campRepository = campRepository;
        this.reservDateRepository = reservDateRepository1;
        this.reservationRepository = reservationRepository1;
        this.reservInfoRepository = reservInfoRepository;
        this.diaryRepository = diaryRepository;
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

    @Override
    public List<ResReservListDTO> getMyReservList(String myEmail) throws Exception {
        List<Reservation> reservations = reservationRepository.findAllByEmail(myEmail);
        if (reservations.isEmpty()) {
            throw new Exception(EMPTY_RESERVATION_ERROR);
        }

        return reservations.stream()
                .map(reservation -> {
                    ReservInfo info = reservInfoRepository.getInfoByResrvationId(reservation.getId());
                    boolean isWritten = false;
                    if( diaryRepository.findDiaryByReservationId(reservation.getId()) != null){
                        isWritten = true;
                    }
                    System.out.println(info);
                    return ResReservListDTO.builder()
                            .id(reservation.getId())
                            .reservation_number(reservation.getReservation_number())
                            .start_date(reservation.getStart_date())
                            .end_date(reservation.getEnd_date())
                            .create_at(reservation.getCreated_at())
                            .isRecordWritten(reservation.is_payment())
                            .camp(reservation.getCampsite_id())
                            .isRecordWritten(isWritten)
                            .info_id(info.getId())
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public ResReservViewDTO getDetail(Long reservationId , String email) throws Exception {
        Optional<Reservation> reservationOpt = reservationRepository.findById(reservationId);
        if (reservationOpt.isEmpty()) {
            throw new Exception(EMPTY_RESERVATION_ERROR);
        }
        Reservation reservation = reservationOpt.get();
        ReservInfo info = reservInfoRepository.getInfoByResrvationId(reservationId);
        if(info.getReservation().getMember().getEmail().equals(email)){
            return ResReservViewDTO.builder()
                    .info(info)
                    .reservation(reservation)
                    .camp(reservation.getCampsite_id())
                    .build();
        }
        else{
            throw(new Exception(NOT_UR_RESERVATION));
        }
    }
}