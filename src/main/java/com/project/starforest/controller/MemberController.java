package com.project.starforest.controller;


import com.project.starforest.dto.member.*;
import com.project.starforest.service.MailService;
import com.project.starforest.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;
    private final MailService mailService;
    private static final int CODE_LENGTH = 6;
    private static final SecureRandom random = new SecureRandom();

    @PostMapping("/register")           //완료_bosco
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO registerDTO) {
        try {
            if (memberService.register(registerDTO)) {
                return ResponseEntity.ok("회원등록 완료.");
            } else return ResponseEntity.badRequest().body("회원등록실패");
        } catch (Exception e) {
            log.info("회원 등록중 에러 발생");
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("회원등록중 에러 발생");
        }
    }

    @PostMapping("/changepw")         //진행중_bosco
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        try {
            return ResponseEntity.ok("회원등록 완료.");
        } catch (Exception e) {
            log.info("패스워드 처리중 에러 발생");
            return ResponseEntity.badRequest().body("패스워드 변경중 에러 발생");
        }
    }

    @PostMapping("/checkpw")          //완료_bosco
    public boolean checkPassword(@RequestBody CheckPassWordDTO checkPassWordDTO) throws Exception {
        try {
            ResultChekPassWordDTO res = memberService.checkPassword(checkPassWordDTO);
            return res.isResult();
        } catch (Exception e) {
            log.info("패스워드 에러 발생");
            return false;
        }
    }

    @PostMapping("/checknick")          //완료_bosco
    public boolean checkNickname(@RequestBody CheckNicknameDTO checkNicknameDTO) throws Exception {
        try {
            CheckNicknameDTO res = memberService.checkNickName(checkNicknameDTO);
            return res.isResult();
        } catch (Exception e) {
            log.info("닉네임 체크 에러 발생");
            return false;
        }
    }

    @PostMapping("/update")          //진행중_ bosco
    public boolean updateMember(@RequestBody ResponseMemberDTO responseMemberDTO) throws Exception {
        try {
            return true;
        } catch (Exception e) {
            log.info("유저 업데이트 에러 발생");
            return false;
        }
    }

    @PostMapping("/checkemail")          //완료_bosco
    public ResponseEntity<CheckEmailValidResponseDTO> checkEmail(@RequestBody CheckEmailValidDTO checkEmailValidDTO) throws Exception {
        try {
            if(memberService.checkEmail(checkEmailValidDTO.getEmail())){
                System.out.println("중복된 이메일입니다.");
                return ResponseEntity.ok(CheckEmailValidResponseDTO.builder()
                        .email(checkEmailValidDTO.getEmail())
                        .secretKey("error")
                        .createdAt(LocalDateTime.now())
                        .isDone(false)
                        .build());
            }
            String secretKey = generateVerificationCode(CODE_LENGTH);
            String title = "별숲 회원가입 인증 메일입니다.";
            String text = "인증번호 : "+ secretKey;
            mailService.sendEmail(checkEmailValidDTO.getEmail(),title,text);
            CheckEmailValidResponseDTO res = CheckEmailValidResponseDTO.builder()
                    .email(checkEmailValidDTO.getEmail())
                    .secretKey(secretKey)
                    .createdAt(LocalDateTime.now())
                    .isDone(true)
                    .build();
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            log.info("이메일 체크 에러 발생");
            return ResponseEntity.badRequest().body(CheckEmailValidResponseDTO.builder()
                    .email(checkEmailValidDTO.getEmail())
                    .secretKey("error")
                    .createdAt(LocalDateTime.now())
                    .isDone(false)
                    .build());
        }
    }

    private String generateVerificationCode(int length) {
        StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            code.append(random.nextInt(10)); // 0~9 사이의 난수 추가
        }
        return code.toString();
    }
}

//    boolean register(RegisterDTO registerDTO) throws Exception;
//    ResponseMemberDTO findByEmail(String email)throws Exception;
//    ResponseMemberDTO updateUser(RegisterDTO registerDTO)throws Exception;