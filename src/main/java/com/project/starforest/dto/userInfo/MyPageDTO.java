package com.project.starforest.dto.userInfo;

import com.project.starforest.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyPageDTO {
    private List<Reservation> reservation;
    private List<Diary> diary;
    private List<CampSiteLike> like;
    private List<Order> order;
    private List<ProductReview> rivew;
    private boolean result;
}