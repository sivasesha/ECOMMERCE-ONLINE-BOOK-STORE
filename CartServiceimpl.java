package com.nit.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.entity.Books;
import com.nit.entity.Cart;
import com.nit.entity.Customer;
import com.nit.repo.BooksRepo;
import com.nit.repo.CartRepo;
import com.nit.repo.CustomerRepo;
import com.nit.service.CartService;

@Service
public class CartServiceimpl implements CartService {

	
	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private BooksRepo booksRepo;
	@Autowired
	private CustomerRepo customerRepo;	
	@Override
	public Cart addItemsToCart(Integer custid, Integer booksid, int qty) {
		
		//check customer is there or not there 
		Customer  customer = customerRepo.findById(custid).orElseThrow(()->new  IllegalArgumentException("Customer Id NOt FOund"));
		
		//check books are there not ther 
		Books books = booksRepo.findById(booksid)
		.orElseThrow(()->new IllegalArgumentException("Books Not FOund"));
		
		//Check whether this customer already added this book in their cart
	    // It helps to avoid duplicate entries for the same book

		Cart customerandBooks = cartRepo.findByCustomerAndBooks(customer, books);
		//if cart is not null 
		if(customerandBooks!=null) {
			
			customerandBooks.setQty(customerandBooks.getQty()+qty);
		}
		else {
			//if cart is null
			customerandBooks=new Cart(qty, books, customer);
		}
		
		//calculate the total price (price*qty)
		customerandBooks.setTotalprice(customerandBooks.getQty()*books.getPrice());
		
		return cartRepo.save(customerandBooks);
	}
	
	@Override
	public boolean deleteCartById(Integer id) {
		if(cartRepo.existsById(id)) {
		
	cartRepo.deleteById(id);
	return true;
	}else {
		return false;
	}
	
	}

}
