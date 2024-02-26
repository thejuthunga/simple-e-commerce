package com.ffe.ecommerce.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table(name = "shopper")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "shopper_gen")
	@SequenceGenerator(name = "shopper_gen",initialValue = 100,allocationSize = 5,sequenceName = "shopper_seq_info")
	private int userid;
	private String name;
	private String role;

	private String email;
	
	
	private String password;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "users",cascade = CascadeType.ALL)
	private List<Product> products;
	
	@JsonIgnore
	@OneToMany(mappedBy = "users",cascade = CascadeType.ALL)
	private List<Orders> orders;
	
	//getters and setters
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	
	
}
