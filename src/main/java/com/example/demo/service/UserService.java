package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.UserEntity;
import com.example.demo.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	public Optional<UserEntity> findByEmail(String email) {
		return Optional.ofNullable(userRepository.findByEmail(email));
	}
}
