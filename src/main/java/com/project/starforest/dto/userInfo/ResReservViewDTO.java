package com.project.starforest.dto.userInfo;

import com.project.starforest.domain.CampSite;
import com.project.starforest.domain.ReservInfo;
import com.project.starforest.domain.Reservation;
import com.project.starforest.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResReservViewDTO {
    private ReservInfo info;
    private Reservation reservation;
    private CampSite camp;
}