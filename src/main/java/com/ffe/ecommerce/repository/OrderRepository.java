package com.ffe.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ffe.ecommerce.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
	
	public List<Orders> findByUsersUserid(int userId);
}
