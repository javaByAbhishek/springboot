package com.springboot.restservices.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.restservices.entities.User;
import com.springboot.restservices.exception.UserExistException;
import com.springboot.restservices.exception.UserNameNotFoundException;
import com.springboot.restservices.exception.UserNotFoundException;
import com.springboot.restservices.service.UserService;

@RestController
@Validated
public class UserController {

	@Autowired
	private UserService userService;

	// getAllUser
	@GetMapping(value = "/users")
	public List<User> getAllUser() {
		System.out.println("get all user:Controller");
		return userService.getAllUser();
	}

	// createUser
	@PostMapping(value = "/users")
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder) {
		System.out.println("create User:Controller");
		try {
			userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (UserExistException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	// getUserById
	@GetMapping(value = "/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		System.out.println("get user by id : Controller");
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	// updateUserById
	@PutMapping(value = "/users/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		System.out.println("updateUserById : Controller");
		try {
			return userService.updateUserById(id, user);
		} catch (UserNotFoundException e) {

			// e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	// getUserByUserName
	// Global Exception Handler
	/*
	 * { "timestamp": "2019-09-18T07:37:55.945+0000", "status": 500, "error":
	 * "Internal Server Error", "message": "UserName raja not found in DB", "path":
	 * "/users/byUserName/raja" }
	 */
	@GetMapping(value = "/users/byUserName/{userName}")
	public User getUserByUserName(@PathVariable("userName") String userName) throws UserNameNotFoundException {
		System.out.println("get User By User Name :Controller");
		User user = userService.getUserByUserName(userName);
		if (user == null) {
			throw new UserNameNotFoundException("UserName " + userName + " not found in DB");

		}
		return user;
	}

}
