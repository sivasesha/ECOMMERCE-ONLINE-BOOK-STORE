package com.nit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.entity.Books;

public interface BooksRepo extends JpaRepository<Books, Integer> {

}
