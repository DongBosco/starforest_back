package com.project.starforest.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

	@Column(unique = true, nullable = false)
	private Long id;

	private String pass_word;

	@ElementCollection
	@Singular("role")
	private List<MemberRole> memberRoleList = new ArrayList<>();

	public List<String> getRoleNames() {
		return memberRoleList.stream()
				.map(Enum::name)
				.collect(Collectors.toList());
	}
	public void addRole(MemberRole memberRole) {
		memberRoleList.add(memberRole);
	}

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
