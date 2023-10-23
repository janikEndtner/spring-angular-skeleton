package com.example.demo.controller;

import java.security.Principal;

import com.example.demo.dto.MapperService;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final MapperService mapperService;

	@GetMapping("/user/current")
	public UserDTO getCurrentUser(Principal principal) {
		var username = principal.getName();
		var user = userService.findByEmail(username).orElseThrow();
		return mapperService.userToDTO(user);
	}


}
