package com.ffe.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ffe.ecommerce.entity.Reviews;

public interface ReviewRepository extends JpaRepository<Reviews, Integer>{

}
