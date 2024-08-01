package com.project.starforest.dto.userInfo;

import com.project.starforest.domain.CampSite;
import com.project.starforest.domain.ReservInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResReservListDTO {
    private Long id;
    private LocalDateTime create_at;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private String reservation_number;
    private boolean isRecordWritten;
    private CampSite camp;
    private ReservInfo info;
}
