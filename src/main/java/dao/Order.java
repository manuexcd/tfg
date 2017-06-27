package dao;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "order")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order implements Serializable {

	private static final long serialVersionUID = 6892693125355139371L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="orderID", unique = true, nullable = false)
	private long id;
	@Column(name="orderTotalPrice", unique = false, nullable = false)
	private double totalPrice;
	@Column(name="orderDate", unique = false, nullable = false)
	private Date date;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="order", orphanRemoval = true)
	@JoinColumn(name="orderID")
	private List<OrderLine> orderLines;
	@ManyToOne
	@JoinColumn(name="userID")
	private User user;

	/**
	 * @return the totalPrice
	 */
	public double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice
	 *            the totalPrice to set
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
}
