package com.uca.tfg.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.uca.tfg.model.Image;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ProductDTO implements Serializable {
	private static final long serialVersionUID = 4340552175235204140L;
	@ToString.Exclude
	private long id;
	@NonNull
	private String name;
	@NonNull
	private String description;
	@NonNull
	private double price;
	@NonNull
	private int stockAvailable;
	@ToString.Exclude
	private boolean isVisible = true;
	@ToString.Exclude
	private Image productImage;

	public void updateStock(int stock) {
		this.stockAvailable += stock;
	}
}
