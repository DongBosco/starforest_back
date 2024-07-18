package com.project.starforest.security;
import com.project.starforest.domain.Member;
import com.project.starforest.dto.MemberDTO;
import com.project.starforest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("------------------ #2 loadUserByUsername-------------------------- " + username);

        Member member = memberRepository.getWithRole(username);
        if(member == null) {

            System.out.println("not found");
            throw new UsernameNotFoundException("not found");
        }

        MemberDTO memberDTO = new MemberDTO(
                member.getEmail(),
                member.getPw(),
                member.getMemberRoleList());



        return memberDTO;
    }

}