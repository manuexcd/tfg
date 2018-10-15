package com.uca.tfg.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "user_table")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Data
@NoArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = 7110275440135292814L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ToString.Exclude
	@Column(name = "userID", unique = true, nullable = false)
	private long id;
	@Column(name = "userName", unique = false, nullable = false, length = 20)
	private String name;
	@Column(name = "userSurname", unique = false, nullable = false, length = 40)
	private String surname;
	@Column(name = "userAddress", unique = true, nullable = false, length = 120)
	private String address;
	@Column(name = "userPhone", unique = true, nullable = false, length = 18)
	private String phone;
	@Column(name = "userEmail", unique = true, nullable = false, length = 50)
	private String email;
	@ToString.Exclude
	@Column(name = "userPassword", unique = false, nullable = false, length = 1000)
	private String password;
	@ToString.Exclude
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Collection<Order> orders;
	@ToString.Exclude
	@OneToOne
	private Image userImage;
	@ToString.Exclude
	@ElementCollection(fetch = FetchType.LAZY)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<String> roles;

	public User(String email, String password) {
		this.setEmail(email);
		this.setPassword(new BCryptPasswordEncoder().encode(password));
	}

	public User(String name, String surname, String address, String phone, String email, String password) {
		super();
		this.setName(name);
		this.setSurname(surname);
		this.setAddress(address);
		this.setPhone(phone);
		this.setEmail(email);
		this.setPassword(new BCryptPasswordEncoder().encode(password));
	}
	
	public User(String name, String surname, String address, String phone, String email, String password, Image image) {
		super();
		this.setName(name);
		this.setSurname(surname);
		this.setAddress(address);
		this.setPhone(phone);
		this.setEmail(email);
		this.setPassword(new BCryptPasswordEncoder().encode(password));
		this.setUserImage(image);
	}

	public String getFullName() {
		return this.name.concat(" ").concat(this.surname);
	}
}
