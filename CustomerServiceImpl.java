package com.nit.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nit.entity.Customer;
import com.nit.exception.CustomerIdNotFound;
import com.nit.repo.CustomerRep;
import com.nit.repo.CustomerRepo;
import com.nit.repo.FilereadRepo;
import com.nit.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final FilereadRepo filereadRepo;

	
	@Autowired
	private CustomerRepo custRepo;
	
	@Autowired
	CustomerRep customerRep;

    CustomerServiceImpl(FilereadRepo filereadRepo) {
        this.filereadRepo = filereadRepo;
    }
	@Override
	public Customer customerSave(Customer cust) {
		
		try {
			Customer cus=new Customer();
			cus.setName(cust.getName());
			cus.setEmail(cust.getEmail());; 
			return custRepo.save(cust);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while saving customer: " + e.getMessage());
		}
		
	}
	@Override
	public Customer customerUpdate(Customer cus) {
		Optional<Customer> customerId = custRepo.findById(cus.getId());
		if(customerId.isPresent()) {
			Customer customer = customerId.get();
			customer.setEmail(cus.getEmail());
			customer.setName(cus.getName());
			return custRepo.save(customer);
			}
		else {
			throw new CustomerIdNotFound("Customer not found with the id::"+cus.getId());
		}
		
	}
	
	@Override
	public Customer customerSaveOrUpdate(Customer cust) {
		if(cust.getId()==null) {
			return customerSave(cust);
		}else {
		Optional<Customer> byId = custRepo.findById(cust.getId());
		if(byId.isPresent()) {
			return customerUpdate(cust);
		}
        else {
        	//id provided but nod data treat as new record
        	
			return customerSave(cust);
			
		}
		}
	}
	
	
	@Override
	public Customer customerfindById(Integer id) {
		Optional<Customer> byId = custRepo.findById(id);
		
		if(byId.isEmpty()) {
			throw new CustomerIdNotFound("id Not FOund");
		}
			
		return byId.get();
		
	}
	public List<Customer> customersAll() {
		List<Customer> list = custRepo.findAll();
		return list;
		
		
	}
	@Override
	public Page<Customer> customersAllByPagination(int page, int size, String sortField, String sortOrder) {
		Sort sort=sortOrder.equalsIgnoreCase("asc") 
		? Sort.by(sortField).ascending():Sort.by(sortField).descending();
		
PageRequest pageRequest = PageRequest.of(page, size, sort);
		
		Page<Customer> all = customerRep.findAll(pageRequest);
		
		return all;
	}
	
}

