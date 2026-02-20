package com.nit.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer qty;
	
	private double totalprice;
	
	@ManyToOne
	@JoinColumn(name = "books_id",updatable = false)
	private Books books;
	
	@ManyToOne
	@JoinColumn(name = "customer_id",updatable = false)
	private Customer customer;

	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdBy;
	
	
	@UpdateTimestamp
	@Column
	private LocalDateTime updatedby;


	public Cart(Integer qty, Books books, Customer customer) {
		super();
		this.qty = qty;
		this.books = books;
		this.customer = customer;
	}
	
	
}
