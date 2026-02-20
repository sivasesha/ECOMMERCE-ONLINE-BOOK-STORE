package com.nit.exception;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nit.model.ErrorMessage;
import com.nit.utility.Constants;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomerIdNotFound.class)
	public ResponseEntity<Object> customerIdNotFound(CustomerIdNotFound exc){
		List<String> l=new ArrayList<>();
		l.add("Error Message:Customer ID Not Found");
		l.add("Error Message:"+exc.getLocalizedMessage());
		l.add("TimeStamp::"+System.currentTimeMillis());
		
		ErrorMessage err=new ErrorMessage(HttpURLConnection.HTTP_BAD_GATEWAY, Constants.FAILED, "Customer Not FOund", l);
		return new  ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<Object> bookIdNotFoundEXception(BookNotFoundException exc){
		List<String> l=new ArrayList<>();
		l.add("Error Message:Book ID Not Found");
		l.add("Error Message:"+exc.getLocalizedMessage());
		l.add("TimeStamp::"+System.currentTimeMillis());
		
		ErrorMessage err=new ErrorMessage(HttpURLConnection.HTTP_BAD_GATEWAY, Constants.FAILED, "Customer Not FOund", l);
		return new  ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
		
	}

}
