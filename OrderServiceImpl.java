package com.nit.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.entity.Books;
import com.nit.entity.Customer;
import com.nit.entity.Orders;
import com.nit.entity.UserRegistration;
import com.nit.model.OrdersModuleDto;
import com.nit.model.UserRequestDTO;
import com.nit.repo.CustomerRepo;
import com.nit.repo.OrdersRepo;
import com.nit.repo.UserRepo;
import com.nit.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	OrdersRepo ordersRepo;
	@Override
	public String saveOrder(OrdersModuleDto orderModuleDto) {
		if(orderModuleDto==null ||orderModuleDto.getCustId()==null) {
			return"Please Select at least one Book";
		}
		Integer bookId = orderModuleDto.getBookId();
		Integer custId = orderModuleDto.getCustId();
		String title = orderModuleDto.getTitle(); 
		String[] selectedBooks = title.split(",");
		
		boolean ifprimeUser=checkPrimeUser(custId);
		if(!ifprimeUser) {
			if(selectedBooks.length>1) {
				return"non prime users can slect only one book";
			}
			
			List<Orders> anyLastWeekPlaced = ordersRepo.findAnyLastWeekPlaced(custId);
			if(!anyLastWeekPlaced.isEmpty()) {
				return "Non prime users can purchase only one book in week";
			}
			
			for (String bookTitle:selectedBooks) {
			String bookByName = ordersRepo.findBookByName(bookTitle);
				
				if(bookByName==null) {
					return "No Books Found WIth"+bookTitle;
				}
				
				Orders order=new Orders();
				order.setBookId(bookId);
				order.setCustId(custId);
				order.setStatus(false);
				ordersRepo.save(order);
				
			}
		}
		return "Order placed Sucessfully Thank You";
	}

	private boolean checkPrimeUser(Integer custId) {
	    Optional<Customer> byId = customerRepo.findById(custId);
	    return byId.map(Customer::getPrime).orElse(false);
	}


	

}
