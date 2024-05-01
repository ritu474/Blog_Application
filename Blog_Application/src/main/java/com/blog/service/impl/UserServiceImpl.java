package com.blog.service.impl;

import java.util.List;


import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.exceptions.*;
import com.blog.dto.UserDto;
import com.blog.entity.User;
import com.blog.repository.UserRepository;
import com.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;//To convert the one object with another

	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user=this.dtoToUser(userDto);
		
		User savedUser= this.userRepository.save(user);
		
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","ID",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		
		return this.userToDto(this.userRepository.save(user));
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","ID",userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users = this.userRepository.findAll();	
		
		List<UserDto> userDto= users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		return userDto; 
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user= this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "ID", userId));
		this.userRepository.delete(user);
		
	}
	
	//To convert userDto to user
	private User dtoToUser(UserDto userDto)
	{
		User user=this.modelMapper.map(userDto, User.class);
		
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		return user;
	}
	
	//To convert user to userDto
	private UserDto userToDto(User user)
	{
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		
		return userDto;
	}

}
