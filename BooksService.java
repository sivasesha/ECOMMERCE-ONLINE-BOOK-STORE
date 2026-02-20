package com.nit.service;

import java.util.List;

import com.nit.entity.Books;

public interface BooksService {

	public Books saveCustomerBooks(Books books);
	
	public List<Books> getAllCustomerBooks();
	
	public Books getCustomerBookById(Integer id);
}
