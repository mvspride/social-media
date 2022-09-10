package com.example.social.media;

import com.example.social.media.exceptions.PostNotFoundException;
import com.example.social.media.repos.UserRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SocialMediaApplication {

	public  static void main(String [] args) {

		SpringApplication.run(SocialMediaApplication.class, args);
	}

}
