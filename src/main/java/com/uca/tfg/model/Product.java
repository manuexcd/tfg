package com.uca.tfg.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "product")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product implements Serializable {

	private static final long serialVersionUID = 4340552175235204140L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "productID", unique = true, nullable = false)
	private long id;
	@Column(name = "productName", unique = true, nullable = false, length = 30)
	private String name;
	@Column(name = "productDescription", unique = false, nullable = false, length = 200)
	private String description;
	@Column(name = "productPrice", unique = false, nullable = false)
	private double price;
	@Column(name = "productStock", unique = false, nullable = true)
	private int stockAvailable;
	@Column(name = "isVisible", unique = false, nullable = false)
	private boolean isVisible = true;
	@OneToOne(cascade = CascadeType.MERGE)
	private Image productImage;

	public Product() {
	}

	public Product(String name, String description, double price, int stockAvailable, Image productImage) {
		super();
		this.setName(name);
		this.setDescription(description);
		this.setPrice(price);
		this.setStockAvailable(stockAvailable);
		this.setProductImage(productImage);
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStockAvailable() {
		return stockAvailable;
	}

	public void setStockAvailable(int stockAvailable) {
		this.stockAvailable = stockAvailable;
	}

	public void updateStock(int stock) {
		this.stockAvailable -= stock;
	}
	
	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public Image getProductImage() {
		return this.productImage;
	}
	
	public void setProductImage(Image productImage) {
		this.productImage = productImage;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getName() + ". Stock Available: " + this.getStockAvailable() + ".\n");
		sb.append(this.getPrice() + " €\n");
		sb.append(this.getDescription());
		
		return sb.toString();
	}
}
