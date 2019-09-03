package com.springboot.restservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.restservices.entities.User;
import com.springboot.restservices.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//getAllUser
	@GetMapping(value="/users")
	public List<User> getAllUser(){		
		System.out.println("get all user:Controller");		
		return userService.getAllUser();
	}
	
	//createUser
	@PostMapping(value="/users")
	public User createUser(@RequestBody User user){		
		System.out.println("create User:Controller");		
		return userService.createUser(user);
	}
	
	
	//getUserById
	@GetMapping(value="/users/{id}")
	public Optional<User> getUserById(@PathVariable("id")Long id){		
		System.out.println("get user by id : Controller");		
		return userService.getUserById(id);
	}
	
	
	//updateUserById
	@PutMapping(value="/users/{id}")
	public User updateUserById(@PathVariable("id")Long id,@RequestBody User user){		
		System.out.println("updateUserById : Controller");		
		return userService.updateUserById(id, user);
	}
	
	
	//getUserByUserName
	@GetMapping(value="/users/byUserName/{userName}")
	public User getUserByUserName(@PathVariable("userName")String userName){
		System.out.println("get User By User Name :Controller");
		
		return userService.getUserByUserName(userName);
	}
	
	

}
