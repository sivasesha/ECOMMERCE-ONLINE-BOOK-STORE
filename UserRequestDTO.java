package com.nit.model;

import java.sql.Date;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserRequestDTO {
	
	private String fname;
	@Column
	private String lname;
	@Column
	private String email;
	@Column
	private String pwd;
	@Column
	private String username;


}
