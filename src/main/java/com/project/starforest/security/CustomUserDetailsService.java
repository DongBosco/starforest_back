package com.project.starforest.security;
import com.project.starforest.domain.Member;
import com.project.starforest.domain.UserInfo;
import com.project.starforest.dto.member.MemberDTO;
import com.project.starforest.repository.MemberRepository;
import com.project.starforest.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Log4j2
@ReadingConverter
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("------------------ #2 loadUserByUsername-------------------------- " + username);

        Member member = memberRepository.getWithRole(username);
        UserInfo userInfo = userInfoRepository.getUserInfoById(member.getId());
        if(member.getEmail() == null) {
            System.out.println("not found");
            throw new UsernameNotFoundException("not found");
        }
        if(userInfo == null) {
            System.out.println("userInfo not found");
            throw new UsernameNotFoundException("not found");
        }


        MemberDTO memberDTO = new MemberDTO(
                member.getEmail(),
                member.getPw(),
                userInfo.getIntroduce(),
                userInfo.getNick_name(),
                userInfo.getProfile_url(),
                userInfo.getLogin_type(),
                userInfo.getGrade(),
                member.getMemberRoleList().stream().map(
                        Enum::name).collect(Collectors.toList()));
        log.info(memberDTO);

        return memberDTO;
    }

}