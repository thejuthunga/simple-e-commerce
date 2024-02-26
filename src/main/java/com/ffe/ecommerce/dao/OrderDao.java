package com.ffe.ecommerce.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ffe.ecommerce.entity.Orders;
import com.ffe.ecommerce.repository.OrderRepository;

@Repository
public class OrderDao {
	
	@Autowired
	private OrderRepository repository;
	
	public Orders saveOrder(Orders order) {
		return repository.save(order);
	}
	
	public List<Orders> getAll(int userid){
	  return repository.findByUsersUserid(userid);
	}
}
