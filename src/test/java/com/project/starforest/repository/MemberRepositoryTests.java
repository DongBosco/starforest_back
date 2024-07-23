package com.project.starforest.repository;

import com.project.starforest.domain.Member;
import com.project.starforest.domain.MemberRole;
import com.project.starforest.domain.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class MemberRepositoryTests {
	
	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManagerBuilder authenticationManagerBuilder;

	@Test
	public void testInsertData() {
		for (int i = 0; i < 10; i++) {
			Long temp = (long) (i+11);
			Member member = Member.builder()
					.email("user"+i+"@aaa.com")
					.pass_word(passwordEncoder.encode("1111"))
					.id(temp)
					.build();

			member.addRole(MemberRole.USER);

			UserInfo userInfo = UserInfo.builder()
					.id(member.getId())
					.user_email(member)
					.name("temp")
					.profile_url("https://lgcxydabfbch3774324.cdn.ntruss.com/KBO_IMAGE/person/middle/2024/52330.jpg")
					.introduce("hello_world")
					.nick_name("test")
					.login_type(0)
					.grade(0)
					.build();
			if(i>=5) {
				member.addRole(MemberRole.MEMBER);
				userInfo.setGrade(1);
			}
			if(i>=8) {
				member.addRole(MemberRole.ADMIN);
				userInfo.setGrade(2);
			}
			memberRepository.save(member);
			userInfoRepository.save(userInfo);
		}
		
		
		
	}
	
//	@Test
//	public void testRead() {
//		String email = "user6@aaa.com";
//		Member member = memberRepository.getWithRole(email);
//		
//		System.out.println(member.getMemberRoleList());
//		
//				
//	}
	
}
