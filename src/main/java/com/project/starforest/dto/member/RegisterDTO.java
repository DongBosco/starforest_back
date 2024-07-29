package com.project.starforest.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class RegisterDTO {
    private String email,pass_word, nick_name,profile_url,introduce,name;
    private Long id;
    private Integer login_type, grade;
    private List<String> roleNames= new ArrayList<>();
}
