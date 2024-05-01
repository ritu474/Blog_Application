package com.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {

	private Integer categoryId;
	
	@NotBlank
	@NotEmpty
	@Size(min=3,message="Title is too small")
	private String categoryTitle;
	
}
