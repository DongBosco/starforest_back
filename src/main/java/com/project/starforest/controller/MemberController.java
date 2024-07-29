package com.project.starforest.controller;


import com.project.starforest.dto.member.RegisterDTO;
import com.project.starforest.dto.member.ResponseMemberDTO;
import com.project.starforest.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO registerDTO ) {
        try {
            if(memberService.register(registerDTO)) {
                return ResponseEntity.ok("등록이 완료되었습니다.");
            }
            else return ResponseEntity.badRequest().body("다시 시도하여 주세요.");
        } catch (Exception e) {
            log.info("등록실패");
            return ResponseEntity.badRequest().body("유저등록에 실패하였습니다.");
        }
    }
}

//    boolean register(RegisterDTO registerDTO) throws Exception;
//    ResponseMemberDTO findByEmail(String email)throws Exception;
//    ResponseMemberDTO updateUser(RegisterDTO registerDTO)throws Exception;