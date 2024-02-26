package com.ffe.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ffe.ecommerce.dao.ProductDao;
import com.ffe.ecommerce.dao.ReviewDao;
import com.ffe.ecommerce.dao.UserDao;
import com.ffe.ecommerce.dto.ResponseStructure;
import com.ffe.ecommerce.entity.Product;
import com.ffe.ecommerce.entity.Reviews;
import com.ffe.ecommerce.entity.Users;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewDao reviewDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProductDao productDao;
	
	public ResponseEntity<ResponseStructure<String>> saveReviews(int userid,int productid,Reviews reviews) {
		
		Users user = userDao.validateUser(userid);
		
		Product product = productDao.getProductByID(productid);
		
		if(user != null && product != null) {
			reviews.setProduct(product);
			
			reviewDao.saveReviews(reviews);
			
			ResponseStructure<String> structure=new ResponseStructure<String>();
			structure.setMessage("Review Created");
			structure.setStatusCode(HttpStatus.CREATED.value());
			structure.setData("Review Saved to DataBase");
			
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.CREATED);
		}else {
			return null;
		}
	}
}
