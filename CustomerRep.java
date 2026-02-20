package com.nit.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.nit.entity.Customer;

public interface CustomerRep extends PagingAndSortingRepository<Customer, Integer> {

}
