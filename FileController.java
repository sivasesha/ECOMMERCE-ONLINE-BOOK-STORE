package com.nit.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.nit.entity.FileEntity;
import com.nit.repo.FilereadRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {
	
	@Autowired
private  FilereadRepo repo;

    
	@PostMapping("/uploadFile")
	public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file) throws IOException{
     FileEntity fs=new FileEntity();
     fs.setFileName(file.getOriginalFilename());
     fs.setFileType(file.getContentType());
     fs.setImages(file.getBytes());
     repo.save(fs);
     return ResponseEntity.ok("File inserted sucessfully:"+file.getOriginalFilename());
     
	}
	
	@PostMapping("/uploadFiles")
	public ResponseEntity<List<Object>> uploadFiles(@RequestParam MultipartFile[] files) throws IOException{
      List<Object> list = Arrays.stream(files).map(s->{
    	  try {
    		  return uploadFile(s);
    	  }catch(Exception e) {
    		  return "files not uploaded"+e.getLocalizedMessage();
    	  }
      }).collect(Collectors.toList());
     return ResponseEntity.ok(list);
     
	}

}
