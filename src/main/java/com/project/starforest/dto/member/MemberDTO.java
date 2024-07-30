package com.project.starforest.dto.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import static com.project.starforest.domain.MemberRole.MEMBER;


@Getter
@Setter
public class MemberDTO extends User {
	private String email,pass_word, nick_name,profile_url,introduce;
	private Integer login_type, grade;
	private List<String> roleNames= new ArrayList<>();


	public MemberDTO(String email, String pass_word,String introduce, String nick_name,String profile_url,
					 Integer login_type, Integer grade, List<String> roleNames) {
		super(email,pass_word,roleNames.stream()
				.map(str -> new SimpleGrantedAuthority("ROLE_"+MEMBER)).collect(Collectors.toList()));

		this.email = email;
		this.pass_word = pass_word;
		this.introduce = introduce;
		this.nick_name = nick_name;
		this.profile_url = profile_url;
		this.login_type = login_type;
		this.grade = grade;
		this.roleNames = roleNames;
	}

	public Map<String,Object> getClaims(){
		Map<String,Object> dataMap = new HashMap<>();

		dataMap.put("email",email);
		dataMap.put("pass_word",pass_word);
		dataMap.put("introduce", introduce);
		dataMap.put("nick_name",nick_name);
		dataMap.put("profile_url",profile_url);
		dataMap.put("login_type",login_type);
		dataMap.put("grade",grade);
		dataMap.put("roleNames",roleNames);

		return dataMap;
	}
}