package com.nit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.entity.Review;

public interface ReviewRepo extends JpaRepository<Review,Integer> {

}
