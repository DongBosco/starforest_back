package com.project.starforest.dto.store;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class requestCartDTO {
    private String email,pass_word;
    private boolean result;
}