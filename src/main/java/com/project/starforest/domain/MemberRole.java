package com.project.starforest.domain;

public enum MemberRole {
	USER(0),MEMBER(1),ADMIN(2);
	private final int value;

	MemberRole(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
