package com.ffe.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ffe.ecommerce.dao.ProductDao;
import com.ffe.ecommerce.dao.UserDao;
import com.ffe.ecommerce.dto.ResponseStructure;
import com.ffe.ecommerce.entity.Product;
import com.ffe.ecommerce.entity.Reviews;
import com.ffe.ecommerce.entity.Users;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UserDao userDao;
	
	public ResponseEntity<ResponseStructure<String>> saveProducts(int id,Product product){
		
		Users users = userDao.validateUser(id);
		
		if(users.getRole().equalsIgnoreCase("merchant")) {
			product.setUsers(users);
			productDao.saveProduct(product);
		
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Product created");
		structure.setData("Product added to DataBase");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.CREATED);
		}else if(!users.getRole().equalsIgnoreCase("merchant"))
		{
			ResponseStructure<String> structure=new ResponseStructure<String>();
			structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			structure.setMessage("Sorry you are not allowed to add product");
			structure.setData("This Id Not belongs marchent");
			
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.BAD_REQUEST);
		}else 
		{
			ResponseStructure<String> structure=new ResponseStructure<String>();
			structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			structure.setMessage("Sorry something bad in your request");
			structure.setData("Invalid User");
			
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<ResponseStructure<String>> updateProduct(int userid,int productid,Product product){
		
		Users user = userDao.validateUser(userid);
		
		if(user != null) {
			
			 product.setUsers(user);
			 Product updateProduct = productDao.updateProduct(productid, product);
			 if(updateProduct != null) {
			 ResponseStructure<String> structure=new ResponseStructure<String>();
			 structure.setStatusCode(HttpStatus.OK.value());
			 structure.setMessage("Product Updated");
			 structure.setData("Product Updated in the DataBase");
			 return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.OK);
			 }else {
				 ResponseStructure<String> structure=new ResponseStructure<String>();
				 structure.setStatusCode(HttpStatus.OK.value());
				 structure.setMessage("No Product Found ");
				 structure.setData("Invalid Product Id");
				 return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.OK);

			 }
		}else{
			 ResponseStructure<String> structure=new ResponseStructure<String>();
			 structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			 structure.setMessage("No User Found");
			 structure.setData("Invalid User Id");
			 return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		}
		
	}
	
	public ResponseEntity<ResponseStructure<List<Reviews>>> getAllReviews(int userid,int productid) {
		Users user = userDao.validateUser(userid);
		
		Product product = productDao.getProductByID(productid);
		
		if(product.getUsers().getUserid() == user.getUserid()) {
			List<Reviews> reviews = product.getReviews();
			
			ResponseStructure<List<Reviews>> structure=new ResponseStructure<List<Reviews>>();
			
			structure.setData(reviews);
			structure.setMessage("Review List is found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<List<Reviews>>>(structure,HttpStatus.FOUND);
		}else {
			ResponseStructure<List<Reviews>> structure=new ResponseStructure<List<Reviews>>();
			
			structure.setData(null);
			structure.setMessage("Something wrong in your request");
			structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			
			return new ResponseEntity<ResponseStructure<List<Reviews>>>(structure,HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
