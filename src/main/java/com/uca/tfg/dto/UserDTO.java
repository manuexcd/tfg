package com.uca.tfg.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 7110275440135292814L;
	@ToString.Exclude
	private long id;
	private String name;
	private String surname;
	private String address;
	private String phone;
	private String email;
	@ToString.Exclude
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private Long userImage;
	private List<String> roles;
}
