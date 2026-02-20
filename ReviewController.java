package com.nit.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nit.model.OrdersModuleDto;
import com.nit.model.ResponseMessage;
import com.nit.model.ReviewDto;
import com.nit.serviceImpl.OrderServiceImpl;
import com.nit.serviceImpl.ReviewServImpl;
import com.nit.utility.Constants;

import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/review")
@Tag(name = "ReviewController",description = "ReviewController Controller")

public class ReviewController {
	
	
	@Autowired
	ReviewServImpl reviewServImpl;
	
	@PostMapping("/rateBook")
	public ResponseEntity<ResponseMessage> postMethodName(@RequestBody ReviewDto reviewDto) {
		try {
			 String ratingReview = reviewServImpl.createRatingReview(reviewDto);

			if(ratingReview!=null) {
				
				  return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED,Constants.SUCESS,"Review Completed  successfully",
						  ratingReview));

		        } else {
		            //  4. If service returned a ilure message, send a bad request response
		            return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"Review Failed ",
		            		ratingReview));
		        }

		    } catch (Exception e) {
		        //  5. In case of any unexpected exceptions, log and return an internal error response
		        e.printStackTrace();
		        return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR,Constants.FAILED,
		                "Internal server error"));
		    }
		}

		

}//class

