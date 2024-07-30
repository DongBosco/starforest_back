package com.project.starforest.dto.member;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckPassWordDTO {
    private String email,pass_word;
    private boolean result;
}