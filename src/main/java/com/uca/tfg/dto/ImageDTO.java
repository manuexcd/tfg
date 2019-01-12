package com.uca.tfg.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ImageDTO implements Serializable {
	private static final long serialVersionUID = 2868430411020089105L;
	@ToString.Exclude
	private long id;
	@NonNull
	private String url;
}
