package com.uca.tfg.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "product")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Product implements Serializable {

	private static final long serialVersionUID = 4340552175235204140L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ToString.Exclude
	@Column(name = "productID", unique = true, nullable = false)
	private long id;
	@NonNull
	@Column(name = "productName", unique = true, nullable = false, length = 30)
	private String name;
	@NonNull
	@Column(name = "productDescription", unique = false, nullable = false, length = 200)
	private String description;
	@NonNull
	@Column(name = "productPrice", unique = false, nullable = false)
	private double price;
	@NonNull
	@Column(name = "productStock", unique = false, nullable = true)
	private int stockAvailable;
	@ToString.Exclude
	@Column(name = "isVisible", unique = false, nullable = false)
	private boolean isVisible = true;
	@ToString.Exclude
	@OneToOne
	private Image productImage;
	
	public Product(String name, String description, double price, int stockAvailable, boolean isVisible, Image productImage) {
		this.setName(name);
		this.setDescription(description);
		this.setPrice(price);
		this.setStockAvailable(stockAvailable);
		this.setVisible(isVisible);
		this.setProductImage(productImage);
	}

	public void updateStock(int stock) {
		this.stockAvailable += stock;
	}
}
