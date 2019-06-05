package com.uca.tfg.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProductDTO implements Serializable {
	private static final long serialVersionUID = 4340552175235204140L;
	private long id;
	private String name;
	private String description;
	private double price;
	private int stockAvailable;
	private boolean isVisible = true;
	private ImageDTO productImage;
}
