package com.nit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}
