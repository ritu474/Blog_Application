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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.PostDto;
import com.blog.payloads.ApiResponse;
import com.blog.payloads.AppConstants;
import com.blog.payloads.PostResponse;
import com.blog.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/postPage")
public class PostController {
	
	@Autowired
	private PostService postService;

	//CREATE
	@PostMapping("/user/{userId}/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(@Valid
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId)
	{
		PostDto createPost=postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
		
	}
	
	//GET BY USER
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		List<PostDto> getPost= this.postService.findPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(getPost,HttpStatus.OK);
	}
	
	//GET BY CATEGORY
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCtageory(@PathVariable Integer categoryId){
		List<PostDto> getPost= this.postService.findPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(getPost,HttpStatus.OK);
	}
	
	//GET ALL POST
	@GetMapping("/allposts")
	public ResponseEntity<PostResponse> getAllPost(@RequestParam(value="pageNumber", defaultValue =AppConstants.PAGE_NUMBER ,required=false)Integer pageNumber,
												   @RequestParam(value="pageSize", defaultValue =AppConstants.PAGE_SIZE ,required=false)Integer pageSize,
												   @RequestParam(value="sortBy", defaultValue = AppConstants.SORT_BY,required=false)String sortBy,
												   @RequestParam(value="sortDirection", defaultValue = AppConstants.SORT_DIRECTION ,required=false)String sortDirection)
	{
		return new ResponseEntity<>(this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDirection),HttpStatus.OK);
	}
	
	
	//GET A SINGLE POST BY ID
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
	{
		
		return new ResponseEntity<PostDto>(this.postService.getPostById(postId),HttpStatus.OK);
	}
	
	//DELETE POST
	@DeleteMapping("/deleteposts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId)
	{
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("POST HAVE BEEN DELETED SUCCESSFULLY",true),HttpStatus.OK);
	}
	
	//UPDATE POST
	@PutMapping("/updateposts/{postId}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable Integer postId)
	{
		PostDto updatedPost= this.postService.updatePost(postDto, postId);
		return new ResponseEntity<>(updatedPost,HttpStatus.OK);
	}
	
	//SEARCH POST BY TITLE NAME
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(
			@PathVariable("keywords")String keywords)
	{
		List<PostDto> searchPost=this.postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(searchPost,HttpStatus.OK);
	}
		
	
}
