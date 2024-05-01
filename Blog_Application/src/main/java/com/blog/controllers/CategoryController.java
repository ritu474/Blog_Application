package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.CategoryDto;
import com.blog.payloads.ApiResponse;
import com.blog.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	//Create
	@PostMapping("/createCategory")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto createdCategory= this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createdCategory,HttpStatus.CREATED);
	}
	
	//Update
	@PutMapping("/updateCategory/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("categoryId")Integer Id)
	{
		CategoryDto updatedCategory= this.categoryService.updateCategory(categoryDto,Id);
		return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
	}
	
	//Delete
	@DeleteMapping("/deleteCategory/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId")Integer Id)
	{
		this.categoryService.deleteCategory(Id);
		return new ResponseEntity<>(new ApiResponse("Category have been deleted successfully",true),HttpStatus.OK);
	}
	
	//Get A single Category
	@GetMapping("/getACategory/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId")int Id)
	{
		CategoryDto getCategory= this.categoryService.getACategory(Id);
		return new ResponseEntity<CategoryDto>(getCategory,HttpStatus.OK);
	}
	
	//Get all the Categories
	@GetMapping("/getAllCategories")
	public ResponseEntity<List<CategoryDto>> getAllCategory()
	{
		List<CategoryDto> getAllCategory= this.categoryService.getAllCategories();
		return ResponseEntity.ok(getAllCategory);
	}
	
}
