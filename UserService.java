package com.nit.service;


import org.springframework.web.multipart.MultipartFile;

import com.nit.entity.UserRegistration;
import com.nit.model.UserLoginDTO;
import com.nit.model.UserRequestDTO;

public interface UserService {
	
	
	public UserRegistration insertUser(UserRequestDTO dto);
	
   public UserRegistration checkUser(UserRequestDTO dto);

   public UserRegistration insertUserWithMultipartFiles(UserRequestDTO dto,MultipartFile[] files);
	
}
