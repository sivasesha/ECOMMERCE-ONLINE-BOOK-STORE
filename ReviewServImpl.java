package com.nit.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.entity.Books;
import com.nit.entity.Customer;
import com.nit.entity.Review;
import com.nit.exception.CustomerIdNotFound;
import com.nit.model.ReviewDto;
import com.nit.repo.BooksRepo;
import com.nit.repo.CustomerRepo;
import com.nit.service.ReviewService;

@Service
public class ReviewServImpl implements ReviewService {

	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	BooksRepo booksRepo;
	@Override
	public String createRatingReview(ReviewDto reviewDto) {
		
		//check customer is there or not there 
				Customer  customer = customerRepo.findById(reviewDto.getCustId()).orElseThrow(()->new  IllegalArgumentException("Customer Id NOt FOund"));
				
				//check books are there not ther 
				Books books = booksRepo.findById(reviewDto.getBookId())
				.orElseThrow(()->new IllegalArgumentException("Books Not FOund"));
				
			Review review=new Review();
			review.setBooks(books);
			review.setCustomer(customer);
			review.setRate(reviewDto.getRate());
			review.setReview(reviewDto.getReview());
				
		return "review Co pleted Sucessfully";
	}

}
