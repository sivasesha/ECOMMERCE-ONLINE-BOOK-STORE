package com.nit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.entity.Books;
import com.nit.entity.Cart;
import com.nit.entity.Customer;

public interface CartRepo extends JpaRepository<Cart, Integer> {

	Cart findByCustomerAndBooks(Customer customer,Books books);
}
