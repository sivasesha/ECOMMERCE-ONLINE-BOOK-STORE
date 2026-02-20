package com.nit.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nit.entity.Customer;
import com.nit.model.ResponseMessage;
import com.nit.serviceImpl.CustomerServiceImpl;
import com.nit.utility.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/customer")
@Tag(name = "CustomerRegistrationController",description = "Customerregister register And Login and getParticular Customer")
public class CustomerController {

	@Autowired
	private CustomerServiceImpl custService;
	
	@Operation(summary = "Create Customer Register",description = "e commerece online books store  register the Customers")
    @ApiResponses({
     @ApiResponse(responseCode = "201",description = "user register successfully"),
     @ApiResponse(responseCode = "400",description = "user register failure"),
     @ApiResponse(responseCode = "500",description = "Internal server error")
     })
	@PostMapping("/customerSave")
	public ResponseEntity<ResponseMessage> customerRegsitration(@RequestBody Customer cust) {
		try {
		if (cust.getEmail() == null || cust.getEmail().isEmpty() || cust.getName() == null
				|| cust.getName().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
					HttpURLConnection.HTTP_BAD_GATEWAY, Constants.FAILURE, "Email and name Should not ne Empty"));
		}

		Customer customerSave = custService.customerSave(cust);
		if (customerSave != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpURLConnection.HTTP_CREATED,
					Constants.SUCESS, "Customer Created or Registered Sucessfully"));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ResponseMessage(
					HttpURLConnection.HTTP_BAD_GATEWAY, Constants.FAILURE, "Customer Creation Failed"));

		}
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal Server error"));
		}

	}
	
	@Operation(summary = "Update Customer Register",description = "e commerece online books store  Update the Customers")
    @ApiResponses({
     @ApiResponse(responseCode = "201",description = "user register successfully"),
     @ApiResponse(responseCode = "400",description = "user register failure"),
     @ApiResponse(responseCode = "500",description = "Internal server error")
     })
	@PutMapping("/updateCustomerDetails")
	public ResponseEntity<ResponseMessage>  upadteCustomer(@RequestBody Customer cust){
		try {
			if (cust.getEmail() == null || cust.getEmail().isEmpty() || cust.getName() == null
					|| cust.getName().isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_GATEWAY, Constants.FAILURE, "Email and name Should not ne Empty"));
			}

			Customer customerUpdate = custService.customerUpdate(cust);
			if (customerUpdate != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpURLConnection.HTTP_CREATED,
						Constants.SUCESS, "Customer Updated Sucessfully"));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_GATEWAY, Constants.FAILURE, "Customer Updation Failed"));

			}
			}
			catch(Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal Server error"));
			}
	}
	
	
	
	@Operation(summary = " Customer Register or Update",description = "e commerece online books store  Save or Update the Customers")
    @ApiResponses({
     @ApiResponse(responseCode = "201",description = "user register successfully"),
     @ApiResponse(responseCode = "400",description = "user register failure"),
     @ApiResponse(responseCode = "500",description = "Internal server error")
     })
	@PostMapping("/customerSaveorupdate")
	public ResponseEntity<ResponseMessage>  CustomerSaveorUpdate(@RequestBody Customer cust){

		
		System.out.println("Hii"+cust);
		try {
			if (cust.getEmail() == null || cust.getEmail().isEmpty() || cust.getName() == null
					|| cust.getName().isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_GATEWAY, Constants.FAILURE, "Email and name Should not ne Empty"));
			}
           boolean b= cust.getId()!=null;
           custService.customerSaveOrUpdate(cust);
			if (b) {
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpURLConnection.HTTP_CREATED,
						Constants.SUCESS, "Customer Updated Sucessfully"));
			} else {
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpURLConnection.HTTP_CREATED,
						Constants.SUCESS, "Customer Saved Sucessfully"));
			}
			}
			catch(Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal Server error"));
			}
	}
	
	@Operation(summary = " Customer Details Get ById",description = "e commerece online books store  get CUstomer details")
    @ApiResponses({
     @ApiResponse(responseCode = "201",description = "user register successfully"),
     @ApiResponse(responseCode = "400",description = "user register failure"),
     @ApiResponse(responseCode = "500",description = "Internal server error")
     })
	@GetMapping("/customerFindById/{id}")
	public ResponseEntity<ResponseMessage>  CustomerDeatils(@PathVariable Integer id){
		
		
           Customer customerfindById = custService.customerfindById(id);
			if (customerfindById!=null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpURLConnection.HTTP_CREATED,
						Constants.SUCESS, "Customer Found",customerfindById));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_GATEWAY,
						Constants.FAILURE, "Customer Not Found"));
			}
			
	}
	
	@Operation(summary = " All Customer Details" ,description = "e commerece online books store  geting All CUstomer details")
    @ApiResponses({
     @ApiResponse(responseCode = "201",description = "All Customer successfully"),
     @ApiResponse(responseCode = "400",description = "Customer failure"),
     @ApiResponse(responseCode = "500",description = "Internal server error")
     })
	@GetMapping("/customersAllDetails")
	public ResponseEntity<ResponseMessage>  customerAllDeatils(){
		
		
	
           List<Customer> customersAll = custService.customersAll();
			if (customersAll!=null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpURLConnection.HTTP_CREATED,
						Constants.SUCESS, "Customer Found",customersAll));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_GATEWAY,
						Constants.FAILURE, "Customer Not Found",customersAll));
			}
			
	}
	
	@Operation(summary = " All Customer Details By Pagination" ,description = "e commerece online books store  geting All CUstomer details By Pagination")
    @ApiResponses({
     @ApiResponse(responseCode = "201",description = "All Customer successfully"),
     @ApiResponse(responseCode = "400",description = "Customer failure"),
     @ApiResponse(responseCode = "500",description = "Internal server error")
     })
	@GetMapping("/customersAllDetailsPagination")
	public ResponseEntity<ResponseMessage>  customerAllDeatilsbyPagination(@RequestParam int size, @RequestParam int page,
			 @RequestParam String sortField,
			 @RequestParam String sortOrder){
		
		
	
           Page<Customer> customersAll = custService.customersAllByPagination(page, size, sortField, sortOrder);
			if (customersAll!=null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpURLConnection.HTTP_CREATED,
						Constants.SUCESS, "Customer Found",customersAll));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_GATEWAY,
						Constants.FAILURE, "Customer Not Found",customersAll));
			}
			
	}
	
}
