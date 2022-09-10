package com.example.social.media.controller;

import com.example.social.media.entities.Comment;
import com.example.social.media.repos.PostRepo;
import com.example.social.media.service.PostCommentService;
import com.example.social.media.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("users")
public class PostCommentController {

    @Autowired
    PostCommentService postCommentService;

    @Autowired
    PostService postService;

    @Autowired
    PostRepo postRepo;

    //REMOVE COMMENT FROM POST
    @DeleteMapping("/{userId}/{postId}/comment")
    public void removeCommentFromPost(@PathVariable Long userId, @PathVariable Long postId, @RequestBody Comment comment){
        postCommentService.deleteCommentFromPost(userId,postId,comment);
    }

//    @GetMapping("/user/feed/{postId}")
//    public User getUserByPostId(@PathVariable Long postId){
//        Post currentPost = feedRepo.findById(postId).orElseThrow(()->new PostNotFoundException(postId));
//        return postService.getUserId(postId);
//    }

//    ADD COMMENT TO POST
    @PostMapping("/{userId}/{postId}/comment")
    public void addCommentToPost(@PathVariable Long postId, Long userId, @RequestBody Comment comment){
        postCommentService.addCommentToPost(comment,postId,userId);
    }

   // LIKE COMMENT
    @PostMapping("/{userId}/{postId}/{commentId}")
    public void likeComment(@PathVariable Long postId, @PathVariable Long commentId){
        postCommentService.likeComment(postId,commentId);
    }
//
    //UNLIKE COMMENT
    @PutMapping("/{userId}/{postId}/{commentId}")
    public void unLikeComment(@PathVariable Long postId, @PathVariable Long commentId){
        postCommentService.unLikeComment(postId,commentId);
    }
}
