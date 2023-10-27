package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.respository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;

@SpringBootTest
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	@BeforeEach
	void setUp() {
		userService = new UserService(userRepository);
	}

	@Test
	void testFindByEmail() {
		// Arrange
		String email = "test@example.com";
		User user = new User();
		user.setEmail(email);

		Mockito.when(userRepository.findByEmail(email)).thenReturn(user);

		// Act
		Optional<User> result = userService.findByEmail(email);

		// Assert
		Mockito.verify(userRepository).findByEmail(email);
		assert(result.isPresent());
		assert(result.get().getEmail().equals(email));
	}

	@Test
	void testFindAll() {
		// Arrange
		User user1 = new User();
		User user2 = new User();

		Mockito.when(userRepository.findAll()).thenReturn(List.of(user1, user2));

		// Act
		Iterable<User> result = userService.findAll();

		// Assert
		Mockito.verify(userRepository).findAll();
		assert(result.iterator().hasNext());
	}

	@Test
	void testAddOrUpdateUser() {
		// Arrange
		User user = new User();

		Mockito.when(userRepository.save(user)).thenReturn(user);

		// Act
		User result = userService.addOrUpdateUser(user);

		// Assert
		Mockito.verify(userRepository).save(user);
		assert(result != null);
	}

	@Test
	void testFindById() {
		// Arrange
		long userId = 1L;
		User user = new User();
		user.setId(userId);

		Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

		// Act
		Optional<User> result = userService.findById(userId);

		// Assert
		Mockito.verify(userRepository).findById(userId);
		assert(result.isPresent());
		assert(result.get().getId() == userId);
	}
}
