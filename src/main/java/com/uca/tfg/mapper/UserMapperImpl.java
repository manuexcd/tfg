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
}
