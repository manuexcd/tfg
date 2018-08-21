package com.uca.tfg.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "userTable")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = 7110275440135292814L;
	@Id
	@ToString.Exclude
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userID", unique = true, nullable = false)
	private long id;
	@NonNull
	@Column(name = "userName", unique = false, nullable = false, length = 20)
	private String name;
	@NonNull
	@Column(name = "userSurname", unique = false, nullable = false, length = 40)
	private String surname;
	@NonNull
	@Column(name = "userAddress", unique = true, nullable = false, length = 120)
	private String address;
	@NonNull
	@Column(name = "userPhone", unique = true, nullable = false, length = 18)
	private String phone;
	@NonNull
	@Column(name = "userEmail", unique = true, nullable = false, length = 50)
	private String email;
	@ToString.Exclude
	@NonNull
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonBackReference
	private Collection<Order> orders;
	@ToString.Exclude
	@NonNull
	@OneToOne
	private Image userImage;

	public String getFullName() {
		return this.name.concat(" ").concat(this.surname);
	}
}
