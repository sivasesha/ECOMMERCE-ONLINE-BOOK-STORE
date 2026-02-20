package com.nit.serviceImpl;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nit.entity.FileEntity;
import com.nit.entity.UserRegistration;
import com.nit.entity.mongo.UserRegistrationMongo;
import com.nit.model.UserRequestDTO;
import com.nit.mongo.UserRepoMongo;
import com.nit.repo.FilereadRepo;
import com.nit.repo.UserRepo;
import com.nit.service.UserService;

@Service
public class UserRegistrationServiceImpl  implements UserService{
	
	
	@Autowired
	private UserRepo repo;
	
	@Autowired FilereadRepo fileRepo;
	
	@Autowired
	private UserRepoMongo userRepoMongo;
	
	
	@Override
	public UserRegistration insertUser(UserRequestDTO dto) {
		UserRegistration user=new UserRegistration();
		UserRegistrationMongo mongo=new UserRegistrationMongo();
		try {
		user.setFname(dto.getFname());
		user.setLname(dto.getLname());
		user.setEmail(dto.getEmail());
		user.setPwd(Base64.getEncoder().encodeToString(dto.getPwd().getBytes()));
		repo.save(user);
		
		
		mongo.setFname(dto.getFname());
		mongo.setLname(dto.getLname());
		mongo.setEmail(dto.getEmail());
		mongo.setPwd(Base64.getEncoder().encodeToString(dto.getPwd().getBytes()));
		
		userRepoMongo.save(mongo);
		
		System.out.println("saved in mongodb");
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public UserRegistration checkUser(UserRequestDTO dto) 
	{
 UserRegistration findbyEmail = repo.findByEmail(dto.getEmail());
 if(findbyEmail==null) return null;
	if(findbyEmail.getPwd()==null||findbyEmail.getPwd().isEmpty()) return null;
		
	
			try{
				String pwd=new String(Base64.getDecoder().decode(findbyEmail.getPwd()));
			
			if(pwd.equals(dto.getPwd())) {
				return findbyEmail;
			}
			
		}
			catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	
		
	
		return null;
	}

	@Override
	public UserRegistration insertUserWithMultipartFiles(UserRequestDTO dto, MultipartFile[] files) {
		UserRegistration user=new UserRegistration();
		try {
		user.setFname(dto.getFname());
		user.setLname(dto.getLname());
		user.setEmail(dto.getEmail());
		user.setPwd(Base64.getEncoder().encodeToString(dto.getPwd().getBytes()));
		repo.save(user);
		
		if(files!=null &&files.length>0) {
			for (MultipartFile multipartFile : files) {
				FileEntity fs=new FileEntity();
			     fs.setFileName(multipartFile.getOriginalFilename());
			     fs.setFileType(multipartFile.getContentType());
			     fs.setImages(multipartFile.getBytes());
			     fileRepo.save(fs);
			}
			
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}
}
