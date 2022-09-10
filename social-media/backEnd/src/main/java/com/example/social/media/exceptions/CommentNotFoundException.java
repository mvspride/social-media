package com.example.social.media.exceptions;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException(Long commentId){
        super("Could not find comment with "+commentId+" id");
    }
}
