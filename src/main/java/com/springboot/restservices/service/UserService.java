package com.springboot.restservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restservices.repository.UserRepository;
import com.springboot.restservices.entities.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	//get all user
	public List<User> getAllUser(){
		System.out.println("get all user:Service");
		return userRepository.findAll();
	}
	
	//get all user
	public User createUser(User user){
		System.out.println("create User user:Service");
		return userRepository.save(user);
	}
	
	//getUserById
	public Optional<User> getUserById(Long id){
		System.out.println("getUserById :Service");
		return userRepository.findById(id);
	}
	
	//updateUserById
	public User updateUserById(Long id,User user){
		System.out.println("updateUserById :Service");
		user.setId(id);
		return userRepository.save(user);
	}
	
	//getUserByuserName
	public User getUserByUserName(String userName){
		System.out.println("getUserByUserName :Service");
		
		return userRepository.findByUserName(userName);
	}

}
