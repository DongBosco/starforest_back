package com.project.starforest.service.impl;

import com.project.starforest.domain.Member;
import com.project.starforest.domain.MemberRole;
import com.project.starforest.domain.UserInfo;
import com.project.starforest.dto.member.RegisterDTO;
import com.project.starforest.dto.member.ResponseMemberDTO;
import com.project.starforest.repository.MemberRepository;
import com.project.starforest.repository.UserInfoRepository;
import com.project.starforest.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class MemberServiceImpl implements MemberService {
    private static final String ALREADY_REGISTERED_EMAIL = "이미 등록된 이메일입니다.";
    private static final String REGISTRATION_ERROR = "등록오류";
    private static final String FIND_BY_EMAIL_ERROR = "findByEmail 오류 발생";
    private static final String UPDATE_USER_ERROR = "updateUser 오류 발생";

    private final MemberRepository memberRepository;
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, UserInfoRepository userInfoRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.userInfoRepository = userInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(RegisterDTO registerDTO) throws Exception {
        if (memberRepository.findByEmail(registerDTO.getEmail()) != null) {
            throw new Exception(ALREADY_REGISTERED_EMAIL);
        }

        try {
            Member member = createMember(registerDTO);
            UserInfo userInfo = createUserInfo(registerDTO, member);
            userInfoRepository.save(userInfo);
            return true;
        } catch (Exception e) {
            log.error(REGISTRATION_ERROR, e);
            return false;
        }
    }

    private Member createMember(RegisterDTO registerDTO) {
        return memberRepository.save(Member.builder()
                .email(registerDTO.getEmail())
                .pass_word(passwordEncoder.encode(registerDTO.getPass_word()))
                .role(MemberRole.USER)
                .build());
    }

    private UserInfo createUserInfo(RegisterDTO registerDTO, Member member) {
        return UserInfo.builder()
                .user_email(member)
                .name(registerDTO.getName())
                .profile_url("/assets/images/profileImg.png")
                .introduce(registerDTO.getIntroduce())
                .nick_name(registerDTO.getNick_name())
                .login_type(0)
                .grade(0)
                .build();
    }

    @Override
    public ResponseMemberDTO findByEmail(String email) throws Exception {
        try {
            Member member = memberRepository.findByEmail(email);
            UserInfo userInfo = userInfoRepository.getUserInfoById(member.getId());
            return buildResponseMemberDTO(member, userInfo);
        } catch (Exception e) {
            log.error(FIND_BY_EMAIL_ERROR, e);
            throw e;
        }
    }

    private ResponseMemberDTO buildResponseMemberDTO(Member member, UserInfo userInfo) {
        return ResponseMemberDTO.builder()
                .email(member.getEmail())
                .name(userInfo.getName())
                .nick_name(userInfo.getNick_name())
                .profile_url(userInfo.getProfile_url())
                .introduce(userInfo.getIntroduce())
                .id(member.getId())
                .login_type(userInfo.getLogin_type())
                .grade(userInfo.getGrade())
                .roleNames(member.getRoleNames())
                .build();
    }

    @Override
    public ResponseMemberDTO updateUser(RegisterDTO registerDTO) throws Exception {
        try {
            Member member = updateMember(registerDTO);
            UserInfo userInfo = updateUserInfo(registerDTO, member);
            return buildResponseMemberDTO(member, userInfo);
        } catch (Exception e) {
            log.error(UPDATE_USER_ERROR, e);
            throw e;
        }
    }

    private Member updateMember(RegisterDTO registerDTO) throws Exception {
        Member existingMember = memberRepository.findByEmail(registerDTO.getEmail());
        return memberRepository.save(Member.builder()
                .id(existingMember.getId())
                .email(existingMember.getEmail())
                .pass_word(passwordEncoder.encode(registerDTO.getPass_word()))
                .role(MemberRole.USER)
                .build());
    }

    private UserInfo updateUserInfo(RegisterDTO registerDTO, Member member) throws Exception {
        UserInfo existingUserInfo = userInfoRepository.getUserInfoById(member.getId());
        return userInfoRepository.save(UserInfo.builder()
                .user_email(member)
                .id(existingUserInfo.getId())
                .grade(registerDTO.getGrade())
                .introduce(registerDTO.getIntroduce())
                .login_type(existingUserInfo.getLogin_type())
                .profile_url(registerDTO.getProfile_url())
                .build());
    }
}
