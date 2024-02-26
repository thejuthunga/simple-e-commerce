package com.ffe.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ffe.ecommerce.dao.OrderDao;
import com.ffe.ecommerce.dao.ProductDao;
import com.ffe.ecommerce.dao.UserDao;
import com.ffe.ecommerce.dto.OrderRequest;
import com.ffe.ecommerce.dto.ResponseStructure;
import com.ffe.ecommerce.entity.Orders;
import com.ffe.ecommerce.entity.Product;
import com.ffe.ecommerce.entity.Users;

@Service
public class OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UserDao userDao;
	
	
	public ResponseEntity<ResponseStructure<String>> saveOrder(int userid,OrderRequest orderRequest) {
		
		Orders orders=new Orders();
		
		Users user = userDao.validateUser(userid);
		
		if(user.getRole().equalsIgnoreCase("customer")) {
		orders.setInvoicenumber(orderRequest.getInvoicenumber());
		orders.setPaymentmode(orderRequest.getPaymentmode());
		orders.setUsers(user);
		List<Product> products= orders.getProducts();
		
		for(Integer productId: orderRequest.getProductIds()) {
			Product product = productDao.getProductByID(productId);
			
			products.add(product);
			
			product.getOrders().add(orders);
			
//			productDao.saveProduct(product);
		}		
		
		orders.setProducts(products);
		orderDao.saveOrder(orders);
		
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Order Created");
		structure.setData("Order Successfully stored in DataBase");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.CREATED);
		}else {
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("Sorry your not allowed create orders");
		structure.setData("This Id not belongs customer");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
