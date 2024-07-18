package com.project.starforest.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.project.starforest.domain.MemberRole;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;



public class MemberDTO extends User {
	private String email,pw;
	private List<MemberRole> roleNames= new ArrayList<>();


	public MemberDTO(String email, String pw, List<MemberRole> roleNames) {
		super(email,pw,roleNames.stream()
				.map(str -> new SimpleGrantedAuthority("ROLE_"+str)).collect(Collectors.toList()));

		this.email = email;
		this.pw = pw;
		this.roleNames = roleNames;
	}

	public Map<String,Object> getClaims(){
		Map<String,Object> dataMap = new HashMap<>();

		dataMap.put("email",email);
		dataMap.put("pw",pw);
		dataMap.put("roleNames",roleNames);

		return dataMap;
	}











}
