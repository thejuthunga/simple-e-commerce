package com.ffe.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffe.ecommerce.dto.OrderRequest;
import com.ffe.ecommerce.dto.ResponseStructure;
import com.ffe.ecommerce.entity.Orders;
import com.ffe.ecommerce.entity.Product;
import com.ffe.ecommerce.entity.Reviews;
import com.ffe.ecommerce.entity.Users;
import com.ffe.ecommerce.service.OrderService;
import com.ffe.ecommerce.service.ProductService;
import com.ffe.ecommerce.service.ReviewService;
import com.ffe.ecommerce.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/shopper")
@Tag(name = "Shopper End-Points",description = "Shopper related")
public class ApplicationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Operation(description = "To save Shopper Data",summary = "Register as a Shopper")
	@ApiResponses(value = @ApiResponse(description = "Created",responseCode = "201"))
	@PostMapping(value = "/users")
	public ResponseEntity<ResponseStructure<String>> saveUser(@RequestBody Users users){
	   return userService.saveUser(users);
	}
	
	@Operation(description = "save the product data to the DB",summary = "Saving the product data")
	@ApiResponses(value = @ApiResponse(description = "Created",responseCode = "201"))
	@PostMapping(value = "/users/{userid}",consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseStructure<String>> saveProduct(@PathVariable int userid,@RequestBody Product product){
		return productService.saveProducts(userid, product);
	}
	
	@Operation(description = "saves data with existing Id",summary = "Saves the updated product data")
	@ApiResponses(value = @ApiResponse(description = "Updated",responseCode = "200"))
	@PutMapping(value = "/users/{userid}/{productid}",consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseStructure<String>> updateProduct(@PathVariable int userid,@PathVariable int productid,@RequestBody Product product){
		return productService.updateProduct(userid, productid, product);
	}
	
	@Operation(description = "specified user id and order info is stored in DB",summary = "Place Order for specified customer")
	@ApiResponses(value = @ApiResponse(description = "Created",responseCode = "201"))
	@PostMapping(value = "/users/{userid}/order",consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseStructure<String>> placeOrder(@PathVariable int userid,@RequestBody OrderRequest orderRequest){
		return orderService.saveOrder(userid, orderRequest);
	}
	
	@Operation(description = "Save the review data to corresponding product of corresponding customer ID",summary = "Add a review for existing product")
	@ApiResponses(value = @ApiResponse(description = "Created",responseCode = "201"))
	@PostMapping(value = "/users/{userid}/review/{productid}",consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseStructure<String>> createReview(@PathVariable int userid,@PathVariable int productid,@RequestBody Reviews reviews){
		return reviewService.saveReviews(userid, productid, reviews);
	}
	
	@Operation(description = "return all reviews of a product based on product ID",summary = "Get all the reviews of a product")
	@ApiResponses(value = @ApiResponse(description = "OK",responseCode = "200"))
	@GetMapping(value = "/users/{userid}/{productid}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseStructure<List<Reviews>>> getReviewList(@PathVariable int  userid,@PathVariable int productid){
		return productService.getAllReviews(userid,productid);
	}
	
	@Operation(description = "will find and return all the products",summary = "Return all the products")
	@ApiResponses(value = @ApiResponse(description = "OK",responseCode = "200"))
	@GetMapping(value = "/users/{userid}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseStructure<List<Orders>>> getOrderList(@PathVariable int userid){
		return userService.getAllOrder(userid);
	}
}
