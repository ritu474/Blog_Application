package com.blog.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	//custom methods to find all the post under that user
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	//("SELECT P FROM POST P WHERE P.TITLE LIKE %KEYWORD%")
	List<Post> findByTitleContaining( String title);
	
}
