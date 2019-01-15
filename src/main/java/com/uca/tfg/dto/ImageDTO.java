package com.uca.tfg.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
public class ImageDTO implements Serializable {
	private static final long serialVersionUID = 2868430411020089105L;
	@ToString.Exclude
	private long id;
	private String url;
}
