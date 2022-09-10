package com.example.social.media.service;

import com.example.social.media.entities.Post;
import com.example.social.media.entities.User;
import com.example.social.media.exceptions.PostNotFoundException;
import com.example.social.media.exceptions.UserNotFoundException;
import com.example.social.media.repos.PostRepo;
import com.example.social.media.repos.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGalleryService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserRepo userRepo;

    private static Logger logger = LoggerFactory.getLogger(UserGalleryService.class);


    public void addPostToUser(Post post){
        postRepo.save(post);
        logger.info(post.toString());
        User currentUser = userRepo.findByFirebaseUid(post.getUserFirebaseUid());
        //User currentUser = userRepo.findById(post.getUserId()).orElseThrow(()-> new UserNotFoundException(post.getUserId()));
        currentUser.addToGallery(post);
        userRepo.save(currentUser);
        postRepo.save(post);
    }
    public Post getPost(Long postId){

        return postRepo.findById(postId).orElseThrow(()-> new PostNotFoundException(postId));
    }

    public List<Post> getUserGallery(String firebaseUid){
        User currentUser = userRepo.findByFirebaseUid(firebaseUid);
        return currentUser.getGallery();

    }
    public Post deletePost(Long userId, Long postId){
        Post currentPost = postRepo.findById(postId).orElseThrow(()->new PostNotFoundException(postId));
        User currentUser = userRepo.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
        currentUser.deleteFromGallery(currentPost);
        postRepo.deleteById(postId);
        userRepo.save(currentUser);
        return currentPost;
        }

    public ResponseEntity<Post> editPost(Long userId, Long postId, Post post){
        //User currentUser = userRepo.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
        Post currentPost = postRepo.findById(postId).orElseThrow(()->new PostNotFoundException(postId));
        postRepo.updatePost(post.getCaption(),postId);
        //postRepo.save(currentPost);
        //userRepo.save(currentUser);
        return ResponseEntity.ok(post);

    }
    }


