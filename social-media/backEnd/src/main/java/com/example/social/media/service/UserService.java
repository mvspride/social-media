package com.example.social.media.service;

import com.example.social.media.entities.Post;
import com.example.social.media.exceptions.PostNotFoundException;
import com.example.social.media.exceptions.UserNotFoundException;
import com.example.social.media.entities.User;
import com.example.social.media.repos.PostRepo;
import com.example.social.media.repos.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    PostRepo postRepo;

    private static Logger logger = LoggerFactory.getLogger(UserService.class);


    public void addUser(User user){
        userRepo.save(user);

    }
    public User getUserById(Long id){
        return userRepo.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }

    public Boolean userExists(User user){
        List<User> users = userRepo.findAll();
        for(User i : users){
            if(user.getUsername().equals(i.getUsername())
                    && user.getPassword().equals(i.getPassword())){
                return true;
            }
        }

        return false;
    }

    public void deleteUserById(Long userId){
        userRepo.deleteById(userId);
    }

    public void removeAllUsers(){
        userRepo.deleteAll();
    }

    public List<User> getAllUsers(){
       return userRepo.findAll();
    }


    public Post getPostById(Long postId){
        return postRepo.findById(postId).orElseThrow(()->new PostNotFoundException(postId));
    }

    public void addToBio(Long userId, String text){
        User currentUser = userRepo.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
        currentUser.addToBio(text);
        userRepo.save(currentUser);
    }

    public void saveImage(MultipartFile imageFile)throws IOException {
        String folder = "/Users/studenteng01/Documents/codeDIFF/photos";
        byte[] bytes =imageFile.getBytes();
        String imageName = imageFile.getOriginalFilename();
        System.out.println(imageName);
        Path path = Paths.get(folder + imageName);
        Files.write(path,bytes);
    }
}