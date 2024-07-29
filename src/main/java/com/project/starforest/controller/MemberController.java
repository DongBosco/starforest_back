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
                return ResponseEntity.ok("����� �Ϸ�Ǿ����ϴ�.");
            }
            else return ResponseEntity.badRequest().body("�ٽ� �õ��Ͽ� �ּ���.");
        } catch (Exception e) {
            log.info("��Ͻ���");
            return ResponseEntity.badRequest().body("������Ͽ� �����Ͽ����ϴ�.");
        }
    }
}

//    boolean register(RegisterDTO registerDTO) throws Exception;
//    ResponseMemberDTO findByEmail(String email)throws Exception;
//    ResponseMemberDTO updateUser(RegisterDTO registerDTO)throws Exception;