package com.project.starforest.dto.member;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseMemberDTO {
    private String email,nick_name,profile_url,introduce,name;
    private Integer login_type, grade;
    private List<String> roleNames= new ArrayList<>();
}
