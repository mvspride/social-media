package com.example.social.media.exceptions;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Long id){
        super("Could not find Post with "+id+" id");

    }
}
