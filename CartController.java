package com.nit.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nit.entity.Cart;
import com.nit.model.ResponseMessage;
import com.nit.serviceImpl.CartServiceimpl;
import com.nit.utility.Constants;

import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/Cart")
@RestController
@Tag(name = "CartController",description = "Customer AddBooks")

public class CartController {
	
	@Autowired
	private CartServiceimpl cartServiceimpl;
	
	@PostMapping("/addToCart")
	public ResponseEntity<ResponseMessage> saveTheItemsToCart(@RequestParam Integer custId,@RequestParam Integer booksid,@RequestParam Integer qty){
		try {
			Cart cart = cartServiceimpl.addItemsToCart(custId, booksid, qty);
		
		if(cart!=null) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCESS,"Added TO Cart Sucessfully" ,cart));
		}
		else {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_GATEWAY, Constants.FAILED,"Added TO Cart failed" ,cart));
			
		}
		}catch(Exception e) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,"Internalserver Error" ));
			
		}
		
	}
	
	
	@DeleteMapping("/deleteBooks/{id}")
	public ResponseEntity<ResponseMessage> deleteBooksById(@PathVariable Integer id){
		try {
		boolean isDeleted=cartServiceimpl.deleteCartById(id);
		
		if(isDeleted) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCESS,"Deleted Item From Cart" ,isDeleted));
		}
		else {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_GATEWAY, Constants.FAILED,"Deleting Item From Cart Failed" ,isDeleted));
			
		}
		}catch(Exception e) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,"Internalserver Error" ));
			
		}
		
	}
}
