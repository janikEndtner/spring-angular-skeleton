package com.example.demo.dto;

import com.example.demo.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapperService {

	private final ModelMapper modelMapper = new ModelMapper();

	public UserDTO userToDTO(@NonNull User user) {
		return modelMapper.map(user, UserDTO.class);
	}
}
