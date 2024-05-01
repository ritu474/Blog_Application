package com.blog.service;

import java.util.List;

import com.blog.dto.UserDto;

public interface UserService {
	
	//To create and save the user
	UserDto createUser(UserDto user);
	
	//To update the user details
	UserDto updateUser(UserDto user,Integer userId);
	
	//To get a particular user using the ID
	UserDto getUserById(Integer userId);
	
	//To get a list of all users 
	List<UserDto> getAllUsers();
	
	//To delete a user
	void deleteUser(Integer userId);

}
