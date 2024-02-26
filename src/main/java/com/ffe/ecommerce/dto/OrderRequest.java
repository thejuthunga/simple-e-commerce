package com.ffe.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderRequest {
	private String invoicenumber;
	private String paymentmode;//(COD/UPI/Online)
	
	
	
	private List<Integer> productIds=new ArrayList<Integer>();
	
}
