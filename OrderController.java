package com.nit.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nit.entity.Customer;
import com.nit.model.OrdersModuleDto;
import com.nit.model.ResponseMessage;
import com.nit.serviceImpl.OrderServiceImpl;
import com.nit.utility.Constants;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/orders")
@Tag(name = "OrdersController",description = "Orders Controller")

public class OrderController {
	
	
	@Autowired
	OrderServiceImpl orderServiceImpl;
	
	@PostMapping("/placeorders")
	public ResponseEntity<ResponseMessage> postMethodName(@RequestBody OrdersModuleDto ordersModuleDto) {
		try {
			String saveOrder = orderServiceImpl.saveOrder(ordersModuleDto);

			if(saveOrder.toLowerCase().contains("sucess")) {
				
				  return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED,Constants.SUCESS,"Order placed successfully",
						  saveOrder));

		        } else {
		            //  4. If service returned a ilure message, send a bad request response
		            return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"Order placement failed",
		            		saveOrder));
		        }

		    } catch (Exception e) {
		        //  5. In case of any unexpected exceptions, log and return an internal error response
		        e.printStackTrace();
		        return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR,Constants.FAILED,
		                "Internal server error"));
		    }
		}

		

}//class
