package com.example.social.media.controller;

import com.example.social.media.entities.Post;
import com.example.social.media.entities.User;
import com.example.social.media.service.UserGalleryService;
import com.example.social.media.service.UserPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class UserPostController {

    @Autowired
    UserGalleryService userGalleryService;

    @Autowired
    UserPostService userPostService;

    private static Logger logger = LoggerFactory.getLogger(UserPostController.class);
    // ADD Post TO USER
    @PostMapping("")
    public void addPostToUser(@RequestBody Post post){
        logger.info(post.toString());
        userGalleryService.addPostToUser(post);
    }
    //GET USER GALLERY
    @GetMapping("")
    public List<Post> getUserGallery(@RequestParam String firebaseUid){
        return userGalleryService.getUserGallery(firebaseUid);
    }


    //REMOVE Post FROM USER
    @DeleteMapping("/{postId}")
    public void deletePostFromUser(@PathVariable Long userId, @PathVariable Long postId){
        userGalleryService.deletePost(userId,postId);
    }

    //EDIT User Post
    @PutMapping("/{postId}")
    public ResponseEntity editUserPost(@PathVariable Long userId, @PathVariable Long postId, @RequestBody Post post){
       return userGalleryService.editPost(userId,postId,post);
    }

    //LIKE POST
    @PostMapping("/like")
    public void likePost(@RequestParam String firebaseUid, @RequestParam Long postId){
        userPostService.likePost(firebaseUid,postId);
    }

    //UNLIKE POST
    @PutMapping("/unlike")
    public void unLikePost(@RequestParam String firebaseUid, @RequestParam Long postId){
        userPostService.unLikePost(firebaseUid,postId);
    }
}
