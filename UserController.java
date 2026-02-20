package com.nit.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nit.entity.UserRegistration;
import com.nit.model.ResponseMessage;
import com.nit.model.UserRequestDTO;
import com.nit.serviceImpl.UserRegistrationServiceImpl;
import com.nit.utility.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@Tag(name = "UserRegistrationController",description = "Userregister register And Login")
public class UserController {
	
	@Autowired
	private UserRegistrationServiceImpl service;
	
	
	@Operation(summary = "Create User Register",description = "e commerece online books store  register the users")
    @ApiResponses({
     @ApiResponse(responseCode = "201",description = "user register successfully"),
     @ApiResponse(responseCode = "400",description = "user register failure"),
     @ApiResponse(responseCode = "500",description = "Internal server error")
     })

	@PostMapping("/userRegister")
	public ResponseEntity<ResponseMessage> registerUser(@RequestBody UserRequestDTO dto) {
		try {
		if(dto.getEmail()==null ||dto.getPwd().isEmpty()||dto.getEmail().isEmpty()||dto.getPwd()==null) {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_GATEWAY, Constants.FAILURE, "Email And Pwd Should not be Empty"));
		 }
		UserRegistration userregister = service.insertUser(dto);
		
		if(userregister!=null) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCESS,"User Created Sucessfully"));
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_GATEWAY, Constants.FAILURE, "User Creation failed"));
		}
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "internal Server Error"));
		}
	}
	
	@Operation(summary = "Create User Login",description = "e commerece online books store  Login the users")
    @ApiResponses({
     @ApiResponse(responseCode = "201",description = "user register successfully"),
     @ApiResponse(responseCode = "400",description = "user register failure"),
     @ApiResponse(responseCode = "500",description = "Internal server error")
     })

	@PostMapping("/userLogin")
	public ResponseEntity<ResponseMessage> checkUserDeatils(@RequestBody UserRequestDTO dto){
		 try {  
		if(dto.getEmail()==null||dto.getEmail().isEmpty()||dto.getPwd()==null||dto.getPwd().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.SUCESS, "Email ANd Pwd Should not be empty"));
		}else {
		UserRegistration checkUser = service.checkUser(dto);
	   if(checkUser!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCESS,"Logined  Sucessfully"));
			}
			else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessage(HttpURLConnection.HTTP_UNAUTHORIZED, Constants.FAILURE, "Login failed"));
			}
		}
		 }catch(Exception e) {
			 e.printStackTrace();
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "There Will be Some Internal Error Problem...!"));
		 }
		
		
	}
	
	@Operation(summary = "Create User Register With  files",description = "e commerece online books store  register the users with By Multiple files")
    @ApiResponses({
     @ApiResponse(responseCode = "201",description = "user register successfully"),
     @ApiResponse(responseCode = "400",description = "user register failure"),
     @ApiResponse(responseCode = "500",description = "Internal server error")
     })
	@PostMapping("/userRegisterMultipart")
	public ResponseEntity<ResponseMessage> registerUser( @RequestPart("jsondata") String jsondata,
	        @RequestPart(value = "file", required = false) MultipartFile[] file
) {
		try {
		   ObjectMapper mapper=new ObjectMapper();
		   UserRequestDTO dto = mapper.readValue(jsondata, UserRequestDTO.class);
		UserRegistration userregister = service.insertUserWithMultipartFiles(dto, file);
		
		if(userregister!=null) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCESS,"User Created Sucessfully"));
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_GATEWAY, Constants.FAILURE, "User Creation failed"));
		}
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "internal Server Error"));
		}
	}
}
