package com.project.starforest.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude="memberRoleList")
public class Member {

	@Id
	private String email;
	
	private String passWord;
	private int role; // 정해봅시다.
	
	@ElementCollection
	@Builder.Default
	private List<MemberRole> memberRoleList = new ArrayList<>();
	
	public void addRole(MemberRole memberRole) {
		memberRoleList.add(memberRole);
	}
	
	public void clearRoles() {
		memberRoleList.clear();
	}

	public void changePassword(String pw) {
		this.passWord = pw;
	}

	public String getPw() {
		return passWord;
	}
}
