package com.example.demo.dto;

import java.util.List;
import java.util.Set;

import com.example.demo.entity.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	private Long id;
	private String email;
	private List<String> userRoleDTOs;

	public void setUserRoleDTOs(Set<UserRole> roles) {
		this.userRoleDTOs = roles.stream().map(ur -> ur.getRole().toString()).toList();
	}
}
