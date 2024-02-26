package com.ffe.ecommerce.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ffe.ecommerce.entity.Product;
import com.ffe.ecommerce.repository.ProductRepository;

@Repository
public class ProductDao {
	
	@Autowired
	private ProductRepository repository;
	
	public Product saveProduct(Product product) {
		
		return repository.save(product);
	}
	
	public Product updateProduct(int productid,Product product) {
		Optional<Product> optional = repository.findById(productid);
		
		if(optional.isPresent()) {
			 Product temp = optional.get();
			 
				 product.setProductid(temp.getProductid());
				 
				 return repository.save(product);
			 
		}
		
		return null;
	}
	
	public List<Product> getAllProduct(int userid){
		
		return repository.findByUsersUserid(userid);
	}
	
	public Product getProductByID(int id) {
		Optional<Product> optional = repository.findById(id);
		
		if(optional.isPresent()) {
		  return optional.get();
		}
		return null;
	}
}
