package com.billdesk.usermanagement.service;

import java.util.List;

import com.billdesk.usermanagement.entity.User;
import com.billdesk.usermanagement.requestdto.LoginDto;
import com.billdesk.usermanagement.requestdto.SignUpDto;


public interface IUserService {

	public User insertUser(User u);
	public User deleteUserById(int id);
	public List<User> getAllUser();
	public User editUser(int id, User u);
	ApiResponse signUp(SignUpDto signUpDto);
	ApiResponse login(LoginDto loginDto);
	

}
