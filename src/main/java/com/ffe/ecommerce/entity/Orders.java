package com.ffe.ecommerce.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
@Entity
public class Orders{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "order_gen")
	@SequenceGenerator(name = "order_gen",initialValue = 50,allocationSize = 1,sequenceName = "order_seq_info")
	private int orderid;
	private String invoicenumber;
	private String paymentmode;//(COD/UPI/Online)
	@CreationTimestamp
	private LocalDateTime createdDateTime;
	
	
	@ManyToMany(mappedBy = "orders")
	private List<Product> products=new ArrayList<Product>();
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name ="user_id")
	private Users users;

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getInvoicenumber() {
		return invoicenumber;
	}

	public void setInvoicenumber(String invoicenumber) {
		this.invoicenumber = invoicenumber;
	}

	public String getPaymentmode() {
		return paymentmode;
	}

	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
	
	
}
