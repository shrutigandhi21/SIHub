package com.billdesk.usermanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.billdesk.usermanagement.entity.User;
import com.billdesk.usermanagement.exception.UserAlreadyExist;
import com.billdesk.usermanagement.requestdto.LoginDto;
import com.billdesk.usermanagement.service.ApiResponse;
import com.billdesk.usermanagement.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl service;
	
	@GetMapping(value = "/get",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getUserList(){
		List<User> list1 = service.getAllUser();
		return new ResponseEntity<List<User>>(list1,HttpStatus.OK);
	}
	
	
	
	@PostMapping("/add")
	public ResponseEntity<User> addUser(@Valid @RequestBody User u1)throws UserAlreadyExist{
		try{
			User user1 = service.insertUser(u1);
			user1.setPassword(null);
			return new ResponseEntity(user1,HttpStatus.OK);
		}
		catch(UserAlreadyExist userAlreadyExist) {
			return new ResponseEntity("User Already Exists", HttpStatus.CONFLICT);
		}
		
		}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") int id){
		
		
		return new ResponseEntity<User>(service.deleteUserById(id),HttpStatus.OK);	
		}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<User> editItem(@RequestBody User u, @PathVariable("id") int id) {
		User user3 = service.editUser(id, u);
		return new ResponseEntity<User>(user3, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ApiResponse login(@RequestBody LoginDto loginDto ) {
	    return service.login(loginDto);	
	}

	

	}
