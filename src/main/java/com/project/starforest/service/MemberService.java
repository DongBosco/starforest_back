package com.project.starforest.service;

import com.project.starforest.dto.member.MemberDTO;
import com.project.starforest.dto.member.RegisterDTO;
import com.project.starforest.dto.member.ResponseMemberDTO;
import jakarta.transaction.Transactional;

@Transactional
public interface MemberService {
    boolean register(RegisterDTO registerDTO) throws Exception;

    ResponseMemberDTO findByEmail(String email)throws Exception;

    ResponseMemberDTO updateUser(RegisterDTO registerDTO)throws Exception;
}
