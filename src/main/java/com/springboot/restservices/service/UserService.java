package com.springboot.restservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restservices.repository.UserRepository;
import com.springboot.restservices.entities.User;
import com.springboot.restservices.exception.UserExistException;
import com.springboot.restservices.exception.UserNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	//get all user
	public List<User> getAllUser(){
		System.out.println("get all user:Service");
		return userRepository.findAll();
	}
	
	//create user
	public User createUser(User user)throws UserExistException{
		System.out.println("create User user:Service");
		
		User userExist=userRepository.findByUserName(user.getUserName());
		if(userExist!= null) { 
			throw new UserExistException("User Already Exist in dB");
		}
		return userRepository.save(user);
	}
	
	//getUserById
	public Optional<User> getUserById(Long id) throws UserNotFoundException{
		System.out.println("getUserById :Service");
		Optional<User> user=userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not found in dB");
		}
		return user;
	}
	
	//updateUserById
	public User updateUserById(Long id,User user) throws UserNotFoundException{
		System.out.println("updateUserById :Service");
		
		Optional<User> optionalUser=userRepository.findById(id);
		
		if(!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not found in dB");
		}
		user.setId(id);
		return userRepository.save(user);
	}
	
	//getUserByuserName
	public User getUserByUserName(String userName){
		System.out.println("getUserByUserName :Service");
		
		return userRepository.findByUserName(userName);
	}

}
