package com.nit.model;



import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserLoginDTO {
	
	
	private String email;
	@Column
	private String pwd;
	

}
