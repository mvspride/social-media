package com.example.social.media.controller;

import com.example.social.media.entities.Post;
import com.example.social.media.entities.User;
import com.example.social.media.repos.PostRepo;
import com.example.social.media.service.UserGalleryService;
import com.example.social.media.service.UserPostService;
import com.example.social.media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    UserPostService userPostService;
    UserGalleryService userGalleryService;
    @Autowired
    PostRepo postRepo;

    // NEW USER
    @PostMapping("")
    public ResponseEntity<String> newUser(@RequestBody User user) throws IOException {
        if (userService.userExists(user)) {
            return new ResponseEntity<>("User " + user.getUsername() + " already exist", HttpStatus.IM_USED);
        }

        userService.addUser(user);
        return new ResponseEntity<String>("Welcome " + user.getUsername() + " !", HttpStatus.CREATED);
    }
    // GET ALL USERS
    @GetMapping("")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    //DELETE USER BY ID
    @DeleteMapping("/{id}")
    public void removeUserById(@PathVariable Long id){
        userService.deleteUserById(id);
    }

    // GET USER BY ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    //GET FEED
    @GetMapping("/feed")
    public List<Post> getFeed(){
        return postRepo.findAll();
    }


    //  ADD BIO
    @PostMapping("/{userId}/bio")
    public void addToBio(@PathVariable Long userId, @RequestBody String bio) {
        userService.addToBio(userId,bio);
    }

    @PostMapping("/{userId}/uploadImage")
    public String uploadImage(@PathVariable String userId,@RequestParam("imageFile")MultipartFile imageFile){
        String returnValue ="start";
        try {
            userService.saveImage(imageFile);
            returnValue = "start";
        } catch (IOException e) {
            e.printStackTrace();
            returnValue ="error while Uploading Image";
        }
        return returnValue;
    }

}
