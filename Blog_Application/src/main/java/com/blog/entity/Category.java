package com.blog.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="categories")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="category_id")
	private int categoryId;
	
	@Column(name="category_title",length=100,nullable=false)
	private String categoryTitle; 
	
	//Gives the collection of all unique post which is mapped with the category column of Post
	//mappedBy -- Bidirectional OnetoMany Mapping    
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Post> posts=new HashSet<>();
	
}
