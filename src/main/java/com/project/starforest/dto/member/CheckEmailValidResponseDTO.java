package com.project.starforest.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckEmailValidResponseDTO {
    private String email;
    private String secretKey;
    private LocalDateTime createdAt;
    private boolean isDone;
}