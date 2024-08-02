 package com.project.starforest.service;

import com.project.starforest.dto.member.*;
import jakarta.transaction.Transactional;

@Transactional
public interface MemberService {
    boolean register(RegisterDTO registerDTO) throws Exception;

    ResponseMemberDTO findByEmail(String email)throws Exception;

    ResponseMemberDTO updateUser(RegisterDTO registerDTO)throws Exception;

    ResultChekPassWordDTO checkPassword(CheckPassWordDTO checkPassWordDTO) throws Exception;

    CheckNicknameDTO checkNickName(CheckNicknameDTO checkNicknameDTO) throws Exception;
}
