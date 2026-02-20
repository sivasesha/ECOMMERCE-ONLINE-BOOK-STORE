package com.nit.repo;

import java.io.FileReader;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nit.entity.FileEntity;


public interface FilereadRepo extends JpaRepository<FileEntity, Integer> {


}
