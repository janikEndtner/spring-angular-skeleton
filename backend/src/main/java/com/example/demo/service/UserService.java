package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.User;
import com.example.demo.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	public Optional<User> findByEmail(String email) {
		return Optional.ofNullable(userRepository.findByEmail(email));
	}

	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	public User addOrUpdateUser(User user) {
		return userRepository.save(user);
	}

	public Optional<User> findById(@NonNull long id) {
		return userRepository.findById(id);
	}
}
