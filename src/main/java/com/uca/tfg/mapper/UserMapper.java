package com.uca.tfg.mapper;

import com.uca.tfg.dto.UserDTO;
import com.uca.tfg.dto.UserLoginDTO;
import com.uca.tfg.model.User;

public interface UserMapper extends GenericMapper<User, UserDTO> {
	public UserLoginDTO mapUserToUserLoginDTO(User user);
}
