package com.blog.service.impl;



import java.util.Date;

import java.util.List;
import java.util.stream.Collectors;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.dto.PostDto;
import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.PostResponse;
import com.blog.repository.CategoryRepository;
import com.blog.repository.PostRepository;
import com.blog.repository.UserRepository;
import com.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//TO GET USER DETAILS
	@Autowired
	private UserRepository userRepository;
	
	//TO GET CATEGORY DETAILS
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	//CREATE AND SAVE A POST
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
		User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "ID",userId));
		Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("CATEGORY","ID",categoryId));
		
		Post post= this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post savePost= this.postRepository.save(post);
		
		return this.modelMapper.map(savePost, PostDto.class);
	}

	//UPDATE A POST
	@Override
	public PostDto updatePost(PostDto postDto, Integer id) {
		Post post= this.postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("POST", "ID", id));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		this.postRepository.save(post);
		return this.modelMapper.map(post, PostDto.class);
	}

	//DELETE A POST
	@Override
	public void deletePost(Integer id) {
		
		Post post= this.postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("POST", "ID", id));
		this.postRepository.delete(post);
		
	}

	//GET ALL THE POST
	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDirection) {
		//PAGINATION
		//PAGESIZE -- will contain the number of data's 
		//PAGENUMBER -- Will show the number of pages
		
		//Ternary Operator
		Sort sort=(sortDirection.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

//		if(sortDirection.equalsIgnoreCase("asc"))
//		{
//			sort=Sort.by(sortBy).ascending();
//		}
//		else
//		{
//			sort=Sort.by(sortBy).descending();
//		}
		
		Pageable pageable= PageRequest.of(pageNumber,pageSize, sort);
		Page<Post> pagePost=this.postRepository.findAll(pageable);
		List<Post> allPosts=pagePost.getContent();
		
		List<PostDto> allPostDto= allPosts.stream().map((posts)->this.modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());
		
		 PostResponse postResponse=new PostResponse();
		 postResponse.setContent(allPostDto);
		 postResponse.setPageNumber(pagePost.getNumber());
		 postResponse.setPageSize(pagePost.getSize());
		 postResponse.setTotalElements(pagePost.getTotalElements());
		 postResponse.setTotalPages(pagePost.getTotalPages());
		 postResponse.setLastPage(pagePost.isLast());
		 
		return postResponse;
	}

	//GET A SINGLE POST BY USING ID
	@Override
	public PostDto getPostById(Integer id) {
		
		Post post= this.postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("POST", "ID", id));
		
		return this.modelMapper.map(post, PostDto.class);
	}

	//FIND ALL POST IN A PARTICULAR CATEGORY
	@Override
	public List<PostDto> findPostsByCategory(Integer categoryId){
		
		Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("CATEGORY", "ID", categoryId));
		
		List<Post> posts= this.postRepository.findByCategory(category);
		List<PostDto> postDto= posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDto;
	}

	//FIND ALL POSTS BY A USER
	@Override
	public List<PostDto> findPostsByUser(Integer userId) {
		User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("USER", "ID",userId));
		List<Post> posts= this.postRepository.findByUser(user);
		
		List<PostDto> postDto= posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDto;
	}

	//SEARCH POSTS BY TITLE OR ITS NAME (HERE 'LIKE' KEYWORD QUERY IS GENERATED) 
	@Override
	public List<PostDto> searchPosts(String key) {
		List<Post> posts=this.postRepository.findByTitleContaining(key);
		List<PostDto> postDto= posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
				
		return postDto;
	}

}
