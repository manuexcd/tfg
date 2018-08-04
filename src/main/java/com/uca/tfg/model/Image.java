package com.uca.tfg.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "image")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Image implements Serializable {

	private static final long serialVersionUID = 2868430411020089105L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ToString.Exclude
	@Column(name = "imageID", unique = true, nullable = false)
	private long id;
	@NonNull
	@Column(name = "imageUrl", unique = true, nullable = true)
	private String url;;
}