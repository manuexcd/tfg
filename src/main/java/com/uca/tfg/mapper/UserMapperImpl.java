package com.uca.tfg.mapper;

import org.springframework.stereotype.Component;

import com.uca.tfg.dto.UserDTO;
import com.uca.tfg.dto.UserLoginDTO;
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
	public UserLoginDTO mapUserToUserLoginDTO(User user) {
		UserLoginDTO dto = new UserLoginDTO();
		dto.setId(user.getId());
		dto.setRoles(user.getRoles());
		return dto;
	}
}
