package com.example.demo.entity;

import com.example.demo.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEntity {
	private Long id;
	private String email;
	@JsonIgnore
	private String password;
	private Role role;
	private String extraInfo;
}
