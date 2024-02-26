package com.ffe.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ffe.ecommerce.entity.Product;

public interface ProductRepository  extends JpaRepository<Product, Integer>{
	
	public List<Product> findByUsersUserid(int userId);
}
