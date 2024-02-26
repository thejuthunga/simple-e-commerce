package com.ffe.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ffe.ecommerce.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{
	
	
	public Users findByEmailAndPassword(String email,String password);
	
	
}
