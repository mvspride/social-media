package com.example.social.media.service;

import com.example.social.media.entities.Comment;
import com.example.social.media.entities.Post;
import com.example.social.media.entities.User;
import com.example.social.media.exceptions.CommentNotFoundException;
import com.example.social.media.exceptions.PostNotFoundException;
import com.example.social.media.exceptions.UserNotFoundException;
import com.example.social.media.repos.CommentRepo;
import com.example.social.media.repos.PostRepo;
import com.example.social.media.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostCommentService {

    @Autowired
    private PostRepo postRepo;
    private UserRepo userRepo;
    private CommentRepo commentRepo;
    private Comment comment;

    public void addCommentToPost(Comment comment, Long postId, Long userId){
        Post currentPost = postRepo.findById(postId).orElseThrow(()->new PostNotFoundException(postId));
        User currentUser =  userRepo.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
        currentPost.addComment(comment);
        comment.setUser(currentUser);
        comment.setPost(currentPost);
        commentRepo.save(comment);
        postRepo.save(currentPost);
        userRepo.save(currentUser);
    }
    public void deleteCommentFromPost(Long userId, Long postId, Comment comment){
        Post currentPost = postRepo.findById(postId).orElseThrow(()->new PostNotFoundException(postId));
        comment.setPost(null);
        comment.setUser(null);
        currentPost.deleteComment(comment);
        commentRepo.deleteById(comment.getId());
    }

    public void likeComment(Long postId, Long commentId){
        Comment currentComment = commentRepo.findById(commentId).orElseThrow(()->new CommentNotFoundException(commentId));
        currentComment.likeComment(1);
        commentRepo.save(currentComment);
    }

    public void unLikeComment(Long postId, Long commentId){
        Comment currentComment = commentRepo.findById(commentId).orElseThrow(()->new CommentNotFoundException(commentId));
        currentComment.unLikeComment(1);
        commentRepo.save(currentComment);
    }


}
