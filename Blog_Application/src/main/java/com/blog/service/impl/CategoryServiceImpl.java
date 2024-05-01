package com.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.CategoryDto;
import com.blog.entity.Category;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.repository.CategoryRepository;
import com.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category category= this.modelMapper.map(categoryDto, Category.class);
		Category savedCategory= this.categoryRepository.save(category);
		
		return this.modelMapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer Id) {
		
		Category category=this.categoryRepository.findById(Id).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", Id));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		Category updateCategory= this.categoryRepository.save(category);
		
		return this.modelMapper.map(updateCategory,CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer Id) {
		
		CategoryDto categoryDto= getACategory(Id);
		this.categoryRepository.delete(this.modelMapper.map(categoryDto, Category.class));
		
	}

	@Override
	public CategoryDto getACategory(Integer Id) {
		
		Category category= this.categoryRepository.findById(Id).orElseThrow(()->new ResourceNotFoundException("Category", "Category ID", Id));
		
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		
		List<Category> category= this.categoryRepository.findAll();
		List<CategoryDto> categoryDto= category.stream().map((categoryGet)->this.modelMapper.map(categoryGet, CategoryDto.class)).collect(Collectors.toList());
		
		return categoryDto;
	}
	
	

}
