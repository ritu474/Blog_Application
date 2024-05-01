package com.blog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
//DTO - Data Transfer Object helps to hide the sensitive details and 
//not directly exposing the entity class to the clients
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min=4,message="Username must have minimum of 4 characters")
	private String name;
	
	@Email(message="Email address is not valid")
	private String email;
	
	@NotEmpty
	@Size(min=3,message="Password is too short")
	private String password;
	
	@NotEmpty
	private String about;
	
}
