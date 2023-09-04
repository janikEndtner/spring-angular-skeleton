package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.UserEntity;
import com.example.demo.model.Role;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private static final String EXISTING_EMAIL = "test@test.com";
	private static final String ANOTHER_EMAIL = "next@test.com";

	public Optional<UserEntity> findByEmail(String email) {
		// TODO move this to database
		if (EXISTING_EMAIL.equalsIgnoreCase(email)) {
			var user = new UserEntity();
			user.setId(1L);
			user.setEmail(EXISTING_EMAIL);
			user.setPassword("$2a$12$oCayvX2WHm8L9v3SWKEHNeK2OsyqpzfsCrPPMmM7xHhjxJdseednC"); // test
			user.setRole(Role.ADMIN);
			user.setExtraInfo("admin user test");
			return Optional.of(user);
		}
		if (ANOTHER_EMAIL.equalsIgnoreCase(email)) {
			var user = new UserEntity();
			user.setId(99L);
			user.setEmail(ANOTHER_EMAIL);
			user.setPassword("$2a$12$oCayvX2WHm8L9v3SWKEHNeK2OsyqpzfsCrPPMmM7xHhjxJdseednC"); // test
			user.setRole(Role.USER);
			user.setExtraInfo("admin user test");
			return Optional.of(user);
		}
		return Optional.empty();

	}
}
