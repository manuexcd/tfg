package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "user")
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
	@Column(name = "userPhone", unique = true, nullable = false, length = 12)
	private String phone;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	@JoinColumn(name="userID")
	private List<Order> orders;

	public User() {

	}

	public User(String name, String surname, String address, String phone) {
		super();
		this.setName(name);
		this.setSurname(surname);
		this.setAddress(address);
		this.setPhone(phone);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname
	 *            the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getName() + " " + this.getSurname() + ".\n");
		sb.append(this.getAddress() + ".\n");
		sb.append(this.getPhone());

		return sb.toString();
	}
}
