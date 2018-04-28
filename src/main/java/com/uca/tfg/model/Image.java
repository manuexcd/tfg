package com.uca.tfg.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "image")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Image implements Serializable {

	private static final long serialVersionUID = 2868430411020089105L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "imageID", unique = true, nullable = false)
	private long id;
	@Column(name = "imageUrl", unique = true, nullable = true)
	private String url;;

	public Image() {
	}

	public Image(String url) {
		super();
		this.setUrl(url);
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
