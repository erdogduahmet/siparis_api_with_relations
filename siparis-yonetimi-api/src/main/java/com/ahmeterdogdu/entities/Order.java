package com.ahmeterdogdu.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "order_date")
	private Date orderDate;

	@Column(name = "status")
	private String status;

	// ** ilişkiler **
	@ManyToOne
	private Customer customer;
	
	@ManyToMany
	@JoinTable(name = "order_product",
	joinColumns = @JoinColumn(name="order_id"),
	inverseJoinColumns = @JoinColumn(name="product_id"))
	private List<Product> product;

}
