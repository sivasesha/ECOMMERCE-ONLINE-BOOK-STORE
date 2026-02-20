package com.nit.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.nit.entity.Customer;

public interface CustomerService {

	public Customer customerSave(Customer cust);

	public Customer customerUpdate(Customer cus);
	
	public Customer customerSaveOrUpdate(Customer cust);
	
	public Customer customerfindById(Integer id);
	
	public List<Customer> customersAll();
	
	public Page<Customer> customersAllByPagination(int page,int size,String sortField,String sortOrder);
}
