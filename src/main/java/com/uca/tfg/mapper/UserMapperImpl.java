package com.uca.tfg.mapper;

import org.springframework.stereotype.Component;

import com.uca.tfg.dto.UserDTO;
import com.uca.tfg.model.User;

@Component
public class UserMapperImpl extends GenericMapperImpl<User, UserDTO> implements UserMapper {

	@Override
	public Class<User> getClazz() {
		return User.class;
	}

	@Override
	public Class<UserDTO> getDtoClazz() {
		return UserDTO.class;
	}
	
	@Override
	public UserDTO mapEntityToDto(User entity) {
		UserDTO dto = new UserDTO();
		dto.setName(entity.getName());
		dto.setSurname(entity.getSurname());
		dto.setAddress(entity.getAddress());
		dto.setEmail(entity.getEmail());
		dto.setId(entity.getId());
		dto.setPhone(entity.getPhone());
		dto.setUserImage(entity.getUserImage().getId());
		dto.setRoles(entity.getRoles());
		return dto;
	}
}
