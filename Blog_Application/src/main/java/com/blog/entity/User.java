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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@NoArgsConstructor
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private int id;
	
	@Column(name="user_name", nullable = false, length=100)
	private String name;
	
	@Column(name="user_email", nullable = false)
	private String email;
	
	@Column(name="user_password", nullable = false, length=20)
	private String password;
	
	@Column(name="user_about", length=100)
	private String about;
	
	//Gives the collection of all unique post which is mapped with the category column of Post
	//mappedBy -- Bidirectional OnetoMany Mapping   
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Post> posts=new HashSet<>();
	
}
