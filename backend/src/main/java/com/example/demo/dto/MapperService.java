package com.example.demo.dto;

import java.util.stream.Collectors;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.model.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapperService {

	private final ModelMapper modelMapper = new ModelMapper();

	public UserDTO userToDTO(@NonNull User user) {
		var userDTO = modelMapper.map(user, UserDTO.class);
		userDTO.setUserRoleDTOs(user.getRoles());
		return userDTO;
	}

	public User dtoToUser(@NonNull UserDTO userDTO, @NonNull User user) {
		modelMapper.map(userDTO, user);
		var roles = userDTO.getUserRoleDTOs().stream()
				.map(r -> UserRole.builder()
						.id(user.getId())
						.role(Role.valueOf(r))
						.build()
				)
				.collect(Collectors.toSet());
		user.setRoles(roles);
		return user;
	}
}
