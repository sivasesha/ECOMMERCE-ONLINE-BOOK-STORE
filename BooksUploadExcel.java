package com.nit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.entity.BooksFile;

public interface BooksUploadExcel  extends JpaRepository<BooksFile, Integer>{

}
