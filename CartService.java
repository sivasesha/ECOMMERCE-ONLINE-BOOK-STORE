package com.nit.service;

import com.nit.entity.Cart;

public interface CartService {
	
	public Cart addItemsToCart(Integer custid,Integer booksid,int qty);
	
	public boolean deleteCartById(Integer id);

}
