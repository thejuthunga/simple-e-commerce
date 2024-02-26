package com.ffe.ecommerce.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ffe.ecommerce.entity.Users;
import com.ffe.ecommerce.repository.UserRepository;

@Repository
public class UserDao {
	
	@Autowired
	private UserRepository userRepository;
	
	public Users saveUsers(Users users) {
		return userRepository.save(users);
	}
	
	public Users validateUser(int id) {
		Optional<Users> optional = userRepository.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}
	
	
}
