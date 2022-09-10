package com.example.social.media.service;

import com.example.social.media.entities.Comment;
import com.example.social.media.entities.Post;
import com.example.social.media.entities.User;
import com.example.social.media.exceptions.PostNotFoundException;
import com.example.social.media.exceptions.UserNotFoundException;
import com.example.social.media.repos.CommentRepo;
import com.example.social.media.repos.LikedPostsRepo;
import com.example.social.media.repos.PostRepo;
import com.example.social.media.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPostService {
    @Autowired
    PostRepo postRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    CommentRepo commentRepo;
    LikedPostsRepo likedPostsRepo;


    public void deleteCommentFromPost(Comment comment, Long postId,Long userId){
        Post currentPost = postRepo.findById(postId).orElseThrow(()->new PostNotFoundException(postId));
        commentRepo.deleteById(comment.getId());
        postRepo.save(currentPost);
    }

    public void likePost(String firebaseUid, Long postId){
        User currentUser = userRepo.findByFirebaseUid(firebaseUid);
        Post currentPost = postRepo.findById(postId).orElseThrow(()->new PostNotFoundException(postId));
        currentPost.likePost(1);
        //currentUser.addToUserLikedPosts(currentPost);
        userRepo.save(currentUser);
    }
    public void unLikePost(String firebaseUid, Long postId){
        User currentUser = userRepo.findByFirebaseUid(firebaseUid);
        Post currentPost = postRepo.findById(postId).orElseThrow(()->new PostNotFoundException(postId));

        currentPost.unLikePost(1);
       // currentUser.removeFromUserLikedPosts(currentPost);
        userRepo.save(currentUser);
    }


}
