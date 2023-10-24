package com.example.demo.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.StreamSupport;

import com.example.demo.dto.MapperService;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.helpers.IsAdmin;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/")
public class UserController {

	private final UserService userService;
	private final MapperService mapperService;

	@GetMapping("current")
	public UserDTO getCurrentUser(Principal principal) {
		var username = principal.getName();
		var user = userService.findByEmail(username).orElseThrow();
		return mapperService.userToDTO(user);
	}

	@GetMapping("{id}")
	@IsAdmin
	public UserDTO getUserById(
			@PathVariable long id
	) {
		var user = userService.findById(id).orElseThrow();
		return mapperService.userToDTO(user);
	}

	@GetMapping
	@IsAdmin
	public List<UserDTO> getAllUsers() {
		return StreamSupport.stream(userService.findAll().spliterator(), false)
				.map(mapperService::userToDTO)
				.toList();
	}

	@PostMapping
	@IsAdmin
	public UserDTO addUser(
			@RequestBody UserDTO userDTO
	) {
		var addedUser = userService.addOrUpdateUser(mapperService.dtoToUser(userDTO, new User()));
		return mapperService.userToDTO(addedUser);
	}

	@PutMapping("{id}")
	@IsAdmin
	public UserDTO updateUser(
			@PathVariable long id,
			@RequestBody UserDTO userDTO
	) {
		var user = userService.findById(id)
						.orElseThrow();
		var updatedUser = userService.addOrUpdateUser(mapperService.dtoToUser(userDTO, user));
		return mapperService.userToDTO(updatedUser);
	}

}
