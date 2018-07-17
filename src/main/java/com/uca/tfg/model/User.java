package com.uca.tfg.model;

import java.io.Serializable;
import java.util.Arrays;
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

@Entity
@Table(name = "user_table")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class User implements Serializable {

	private static final long serialVersionUID = 7110275440135292814L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	@Column(name = "userPassword", unique = false, nullable = false, length = 1000)
	private String password;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Collection<Order> orders;
	@OneToOne
	private Image userImage;
	@ElementCollection(fetch = FetchType.LAZY)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<String> roles;

	public User() {
		super();
	}

	public User(String name, String surname, String address, String phone, String email, Collection<Order> orders, Image userImage, String password, String... roles) {
		super();
		this.setName(name);
		this.setSurname(surname);
		this.setAddress(address);
		this.setPhone(phone);
		this.setEmail(email);
		this.setOrders(orders);
		this.setUserImage(userImage);
		this.setPassword(new BCryptPasswordEncoder().encode(password));
		this.setRoles(Arrays.asList(roles));
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Collection<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Image getUserImage() {
		return this.userImage;
	}
	
	public void setUserImage(Image userImage) {
		this.userImage = userImage;
	}
	
	public String getFullName() {
		return this.name.concat(" ").concat(this.surname);
	}
	
	public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
    
	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getName() + " " + this.getSurname() + ".\n");
		sb.append(this.getAddress() + ".\n");
		sb.append(this.getPhone());

		return sb.toString();
	}
}
