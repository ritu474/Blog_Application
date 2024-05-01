package com.blog.service;

import java.util.List;

import com.blog.dto.CategoryDto;

public interface CategoryService {

	//CREATE
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	//UPDATE
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer Id);
	
	//DELETE
	public void deleteCategory(Integer Id);
	
	//GET 
	public CategoryDto getACategory(Integer Id);
	
	//GET ALL
	List<CategoryDto> getAllCategories();
	
}
