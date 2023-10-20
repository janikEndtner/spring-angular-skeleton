package com.example.demo.model;

public enum Role {
	USER,
	ADMIN;

	// UserPrincipal needs role to be prefixed
	public String toUserPrincipalRole() {
		return "ROLE_" + this.toString();
	}
}
