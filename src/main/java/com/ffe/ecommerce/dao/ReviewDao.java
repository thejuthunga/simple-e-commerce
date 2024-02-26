package com.ffe.ecommerce.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ffe.ecommerce.entity.Reviews;
import com.ffe.ecommerce.repository.ReviewRepository;

@Repository
public class ReviewDao {
	
	@Autowired
	private ReviewRepository repository;
	
	public Reviews saveReviews(Reviews reviews) {
		return repository.save(reviews);
	}
}
