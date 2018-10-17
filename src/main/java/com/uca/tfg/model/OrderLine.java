package com.uca.tfg.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "order_line")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class OrderLine implements Serializable {

	private static final long serialVersionUID = 4745195410724554377L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ToString.Exclude
	@Column(name = "orderLineID", unique = true, nullable = false)
	private long id;
	@NonNull
	@OneToOne
	private Product product;
	@NonNull
	@Column(name = "orderLineQuantity", unique = false, nullable = false)
	private int quantity;
	@NonNull
	@ToString.Exclude
	@ManyToOne
	@JsonIgnore
	private Order order;
}
