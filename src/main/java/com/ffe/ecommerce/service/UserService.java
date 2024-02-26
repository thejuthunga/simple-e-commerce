package com.ffe.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ffe.ecommerce.dao.OrderDao;
import com.ffe.ecommerce.dao.UserDao;
import com.ffe.ecommerce.dto.ResponseStructure;
import com.ffe.ecommerce.entity.Orders;
import com.ffe.ecommerce.entity.Users;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	
	@Autowired
	private OrderDao orderDao;
	
	public ResponseEntity<ResponseStructure<String>> saveUser(Users users){
		userDao.saveUsers(users);
		
		
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("User Created");
		structure.setData("User Saved to DataBase");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<List<Orders>>> getAllOrder( int userid){
		
		Users user = userDao.validateUser(userid);
		
		if(user.getRole().equals("customer")) {
		
		List<Orders> orders = orderDao.getAll(userid);
			
		ResponseStructure<List<Orders>> structure=new ResponseStructure<List<Orders>>();
		structure.setStatusCode(HttpStatus.FOUND.value());
		structure.setMessage("Order List Found");
		structure.setData(orders);
		
		return new ResponseEntity<ResponseStructure<List<Orders>>>(structure,HttpStatus.FOUND);
		
		}else {
			ResponseStructure<List<Orders>> structure=new ResponseStructure<List<Orders>>();
			
			structure.setData(null);
			structure.setMessage("Something wrong in your request");
			structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			
			return new ResponseEntity<ResponseStructure<List<Orders>>>(structure,HttpStatus.BAD_REQUEST);
		}
	}
}
