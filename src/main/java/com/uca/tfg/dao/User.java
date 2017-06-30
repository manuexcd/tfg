package com.uca.tfg.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "userTable")
@JsonInclude(JsonInclude.Include.NON_NULL)
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
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Order> orders;

	public User() {

	}

	public User(String name, String surname, String address, String phone, String email) {
		super();
		this.setName(name);
		this.setSurname(surname);
		this.setAddress(address);
		this.setPhone(phone);
		this.setEmail(email);
	}
	
	public long getId() {
		return this.id;
	}

	public Collection<Order> getOrders() {
		return this.orders;
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

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getName() + " " + this.getSurname() + ".\n");
		sb.append(this.getAddress() + ".\n");
		sb.append(this.getPhone());

		return sb.toString();
	}
}
