package com.nit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.entity.UserRegistration;

public interface UserRepo extends JpaRepository<UserRegistration, Long> {

	UserRegistration findByEmail(String email);

}
