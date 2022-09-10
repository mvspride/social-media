package com.example.social.media.exceptions;

public class GalleryNotFoundException extends RuntimeException {
    public GalleryNotFoundException(Long id){
        super("Could not find Gallery with "+id+" id");

    }
}

