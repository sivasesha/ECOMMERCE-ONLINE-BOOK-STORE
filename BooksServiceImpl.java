package com.nit.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.nit.entity.Books;
import com.nit.exception.BookNotFoundException;
import com.nit.repo.BooksRepo;
import com.nit.service.BooksService;

@Service
public class BooksServiceImpl implements BooksService {

	@Autowired
	private BooksRepo booksRepo;
	@Override
	public Books saveCustomerBooks(Books books) {
		Books books1 = booksRepo.save(books);
		return books1;
	}
	
	@Override
	@Cacheable(value = "getAllCustomerBooks")
	public List<Books> getAllCustomerBooks() {
		List<Books> list = booksRepo.findAll();
		System.out.println("All Books OF the customer"+list);
		return list;
	}
	
	@Override
	@Cacheable(cacheNames = "books",key = "#id")
	public Books getCustomerBookById(Integer id) {
		Optional<Books> byId = booksRepo.findById(id);
		if(!byId.isPresent()) {
			throw new BookNotFoundException("With this id"+id+"Book Not Found");
		}
			
		return byId.get();
	}

}
