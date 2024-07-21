package com.project.starforest.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
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
	@Column(unique = true, nullable = false)
	private String email;

	@Column(unique = true, nullable = false)
	private Long id;

	private String pass_word;

	private int role; // 정해봅시다.
	
	@ElementCollection
	@Builder.Default
	private List<MemberRole> member_role_list = new ArrayList<>();
	
	public void addRole(MemberRole memberRole) {
		member_role_list.add(memberRole);
	}
	
	public void clearRoles() {
		member_role_list.clear();
	}

	public void changePassword(String pass_word) {
		this.pass_word = pass_word;
	}

	public String getPw() {
		return this.pass_word;
	}

	public List<MemberRole> getMemberRoleList() {
		return this.member_role_list;
	}
}
