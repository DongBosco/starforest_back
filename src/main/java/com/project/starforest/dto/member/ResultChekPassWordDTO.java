package com.project.starforest.dto.member;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultChekPassWordDTO {
    private String email;
    private boolean result;
}