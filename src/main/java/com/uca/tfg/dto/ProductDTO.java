package com.uca.tfg.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.uca.tfg.model.Image;

import lombok.Data;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProductDTO implements Serializable {
	private static final long serialVersionUID = 4340552175235204140L;
	@ToString.Exclude
	private long id;
	private String name;
	private String description;
	private double price;
	private int stockAvailable;
	@ToString.Exclude
	private boolean isVisible = true;
	@ToString.Exclude
	private Image productImage;
}
