package com.project.starforest.dto.member;

import lombok.Builder;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterDTO {
    private String email,pass_word, nick_name,profile_url,introduce,name;
    private Integer login_type, grade;
    private List<String> roleNames= new ArrayList<>();
}
