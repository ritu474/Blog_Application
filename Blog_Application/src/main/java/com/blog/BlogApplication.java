package com.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
		
		@Bean //By this Spring will automatically create its object and will provide us wherever we autowire it.
		public ModelMapper modelMapper()
		{
			return new ModelMapper();
		}
		
	

}
