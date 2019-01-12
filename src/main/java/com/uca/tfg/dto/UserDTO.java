package com.uca.tfg.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.uca.tfg.model.Image;
import com.uca.tfg.model.Order;

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
	private String password;
	@ToString.Exclude
	private Collection<Order> orders;
	@ToString.Exclude
	private Image userImage;
	@ToString.Exclude
	@ElementCollection(fetch = FetchType.LAZY)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<String> roles;
}
