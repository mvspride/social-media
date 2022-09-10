package com.example.social.media.controller;

import com.example.social.media.entities.Post;
import com.example.social.media.entities.User;
import com.example.social.media.repos.PostRepo;
import com.example.social.media.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    PostRepo postRepo;

    @GetMapping("")
    public List<Post> getFeedPost(){
        return postRepo.findAll();
    }


}
