package com.blog.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="post")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer postId;
	
	@Column(name="post_title",length=100,nullable=false)
	private String title;
	
	@Column(name="post_content",length=1000)
	private String content;
	
	@Column(name="post_imageName")
	private String imageName;
	
	@Column(name="post_addedDate")
	private Date addedDate; 
	
	@ManyToOne
	@JoinColumn(name="category_ID")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="user_ID")
	private User user;
	
	
}
