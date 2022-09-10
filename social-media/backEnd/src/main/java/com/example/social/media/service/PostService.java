package com.example.social.media.service;

import com.example.social.media.entities.Post;
import com.example.social.media.exceptions.PostNotFoundException;
import com.example.social.media.repos.PostRepo;
import com.example.social.media.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserRepo userRepo;

    public Post getPost(Long postId){
        Post currentPost = postRepo.findById(postId).orElseThrow(()->new PostNotFoundException(postId));
        return currentPost;
    }
}
