package com.nit.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column
	private Integer rate;
	
	@Column
	private String review;
	
	@ManyToOne
	@JoinColumn(name = "books_id",updatable = false)
	private Books books;
	
	@ManyToOne
	@JoinColumn(name = "customer_id",updatable = false)
	private Customer customer;
	

}
