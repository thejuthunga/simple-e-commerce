package com.ffe.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Reviews {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "reviews_gen")
	@SequenceGenerator(name = "reviews_gen",initialValue = 500,allocationSize = 1,sequenceName = "review_seq_info")
	private int reviewid;
	private String discription;
	private int ratting;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	//getters and setters
	public int getReviewid() {
		return reviewid;
	}
	public void setReviewid(int reviewid) {
		this.reviewid = reviewid;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public int getRatting() {
		return ratting;
	}
	public void setRatting(int ratting) {
		this.ratting = ratting;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
