package com.project.starforest.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Member {

	@Id
	@Column(unique = true, nullable = false)
	private String email;

	private String pass_word;

	@ElementCollection(fetch = FetchType.EAGER)
	@Builder.Default
	private List<MemberRole> memberRoleList = new ArrayList<>();

	public void addRole(MemberRole memberRole) {
		this.memberRoleList.add(memberRole);
	}

	public List<String> getRoleNames() {
		return memberRoleList.stream()
				.map(Enum::name)
				.collect(Collectors.toList());
	}

//	@ElementCollection
//	@Singular("role")
//	private List<MemberRole> memberRoleList = new ArrayList<>();
//
//	public void addRole(MemberRole memberRole) {
//		memberRoleList.add(memberRole);
//	}

	public void clearRoles() {
		memberRoleList.clear();
	}

	public void changePassword(String pass_word) {
		this.pass_word = pass_word;
	}

	public String getPw() {
		return this.pass_word;
	}
}