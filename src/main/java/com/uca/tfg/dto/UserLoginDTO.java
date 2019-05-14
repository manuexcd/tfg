package com.uca.tfg.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginDTO implements Serializable {
	private static final long serialVersionUID = -4271207730379003188L;
	private long id;
	private List<String> roles;
}
