package com.nit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nit.entity.Books;
import com.nit.entity.Orders;

public interface OrdersRepo extends JpaRepository<Orders, Integer> {
	
	@Query(value = "SELECT * FROM orders o WHERE o.id = :id AND o.created_date > (CURDATE() - INTERVAL 7 DAY)", nativeQuery = true)
	List<Orders> findAnyLastWeekPlaced(@Param("id") Integer id);

	@Query("SELECT b FROM Books b WHERE b.title = :title")
	String findBookByName(@Param("title") String bookTitle);


}
