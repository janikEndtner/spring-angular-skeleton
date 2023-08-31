package com.example.demo.service;

import java.util.Optional;

import javax.swing.text.html.Option;

import com.example.demo.entity.UserEntity;
import com.example.demo.model.Role;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private static final String EXISTING_EMAIL = "test@test.com";
	public Optional<UserEntity> findByEmail(String email) {
		// TODO move this to database
		if (!EXISTING_EMAIL.equalsIgnoreCase(email)) {
			return Optional.empty();
		}
		var user = new UserEntity();
		user.setId(1L);
		user.setEmail(EXISTING_EMAIL);
		user.setPassword("$2a$12$oCayvX2WHm8L9v3SWKEHNeK2OsyqpzfsCrPPMmM7xHhjxJdseednC"); // test
		user.setRole(Role.ADMIN);
		user.setExtraInfo("admin user test");
		return Optional.of(user);
	}
}
