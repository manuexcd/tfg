package com.uca.tfg.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
public class UserLoginDTO implements Serializable {
	private static final long serialVersionUID = -4271207730379003188L;
	@ToString.Exclude
	private long id;
	@ToString.Exclude
	private List<String> roles;
}
