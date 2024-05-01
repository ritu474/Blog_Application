package com.blog.service;

import java.util.List;


import com.blog.dto.PostDto;
import com.blog.payloads.PostResponse;

public interface PostService {

	//CREATE
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//UPDATE
	PostDto updatePost(PostDto postDto,Integer id);
	
	//DELETE
	void deletePost(Integer id);
	
	//GET ALL POST
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDirection);
	
	//GET SINGLE POST
	PostDto getPostById(Integer id);
	
	//GET ALL POST BY CATEGORY
	List<PostDto> findPostsByCategory(Integer categoryId);
	
	//GET ALL POST BY USER
	List<PostDto> findPostsByUser(Integer userId);
	
	//SEARCH POST BY ITS NAME
	List<PostDto> searchPosts(String key);

	
	
}
