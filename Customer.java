package com.nit.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
@Data
public class Customer {
	
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer id;
	
	@Column
	private String name;
	@Column
	private String email;
	
	@CreationTimestamp
	@Column
	private LocalDate craetedBy;
	
	@UpdateTimestamp
	@Column
	private LocalDate updatedBy;
	
	@Column(columnDefinition = "TINYINT(1)")
	private Boolean prime;

}
