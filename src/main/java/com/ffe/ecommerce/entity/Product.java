package com.ffe.ecommerce.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "product_gen")
	@SequenceGenerator(name = "product_gen",initialValue = 3010,allocationSize = 5,sequenceName = "product_seq_info")
	private int productid;
	private String name;
	private double cost;
	private String brandname;
	
	
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
	private List<Reviews> reviews=new ArrayList<Reviews>();
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(joinColumns  = @JoinColumn(name = "product_id"),inverseJoinColumns = @JoinColumn(name = "order_id"))
	private List<Orders> orders=new ArrayList<Orders>();
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users users;

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	public List<Reviews> getReviews() {
		return reviews;
	}

	public void setReviews(List<Reviews> reviews) {
		this.reviews = reviews;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
	
	
	
}
