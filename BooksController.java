package com.nit.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nit.entity.Books;
import com.nit.model.ResponseMessage;
import com.nit.serviceImpl.BooksServiceImpl;
import com.nit.utility.Constants;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/books")
@Tag(name = "BooksController",description = "Customerregister register And Login and getParticular Customer")

public class BooksController {

	@Autowired
	private BooksServiceImpl  booksServiceImpl;
	
	private static final Logger logger=LoggerFactory.getLogger(BooksController.class);
	@PostMapping("/saveBooks")
	public ResponseEntity<ResponseMessage> customeSaveBooks(@RequestBody Books books){
		logger.info("Books Controller layer Calling Satrted");
		try {
			
			if(books.getName()==null||books.getName().isEmpty()||books.getTitle().isEmpty()||books.getTitle()==null) {
				logger.debug("Recived Msg Data");
				logger.warn("Missing Book Name And Title");
				logger.error("Books Registration ,Books Name And Title Is Misisng");
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_GATEWAY, Constants.FAILED, "Name And title Should not be Empty"));
				
				
			}
			logger.info("message return to ECo_system=\"");
			Books saveCustomerBooks = booksServiceImpl.saveCustomerBooks(books);
			if(saveCustomerBooks!=null) {
				 logger.info("Message return eco-system = \"BOOKSTORE_ONLINE_REGISTRATION_CREATION_SUCCESS\" .");    

				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCESS, "CustomerBooksSavedSucessfully",saveCustomerBooks));
				
			}else {
				
				 logger.info("Message return eco-system = \"BOOKSTORE_ONLINE_REGISTRATION_CREATION_FAILED\" ."); 
	             logger.info("Book  Registration controller layer calling completed");  
	             logger.warn("Books Registration service return null : registration failed"); 

				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "CustomerBookssaving failed",saveCustomerBooks));
				
			}
		}
		catch(Exception e){
			
			 logger.error("New user creation process failed in Bookstore-DB . Exception:" +e.getMessage());
			return  ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR,Constants.FAILURE , e.getLocalizedMessage()));
		}
		
	}
	
	
	@GetMapping("/getAllBooks")
	public ResponseEntity<ResponseMessage> getAllBooksOfCustomer(){
		
		try {
			List<Books> allCustomerBooks = booksServiceImpl.getAllCustomerBooks();
			if(allCustomerBooks!=null) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCESS, "Customer Books retrived Sucesfully",allCustomerBooks));
				
			}else {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Customer Books Retriving failed",allCustomerBooks));
				
			}
		}
		catch(Exception e){
			return  ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR,Constants.FAILURE , e.getLocalizedMessage()));
		}
		
	}
	
	@GetMapping("/getbook/{id}")
	public ResponseEntity<ResponseMessage> getCustomerBookbyIds(@PathVariable Integer id){
		
		try {
			 Books customerBookById = booksServiceImpl.getCustomerBookById(id);
			if(customerBookById!=null) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCESS, "Customer Books Fetched Sucesfully",customerBookById));
				
			}else {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Customer Books Not Found ",customerBookById));
				
			}
		}
		catch(Exception e){
			return  ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR,Constants.FAILURE , e.getLocalizedMessage()));
		}
		
	}
	
	
	
	
}
