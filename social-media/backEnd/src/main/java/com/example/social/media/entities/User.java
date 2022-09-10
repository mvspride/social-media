package com.example.social.media.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String firebaseUid;

    @OneToMany
    private List<Post> saved;

    @OneToMany
    private List<Post> gallery;

    private String email;
    private String username;
    private String password;
    private String bio ="";
    private String profileImageURL ="";

    @CreationTimestamp
    private Timestamp timeStamp;

    public List<Post> getGallery() {
        return gallery;
    }


    @OneToMany(cascade = CascadeType.ALL)
    private List<Post> userLikedPosts;


    public List<Post> getSaved() {
        return saved;
    }

    public void addToSaved(Post post){
        saved.add(post);
    }

    public List<Post> getUserLikedPosts() {
        return userLikedPosts;
    }



    public Post addToGallery(Post post){
        gallery.add(post);
        return post;
    }

    public Post deleteFromGallery(Post post){
        gallery.remove(post);
        return post;
    }

    public List<Post> LikedPosts() {
        return userLikedPosts;
    }

    public void addToLikedPosts(Post post){
        userLikedPosts.add(post);
    }
    public void removeFromLikedPosts(Post post){
        userLikedPosts.remove(post);
    }
    public String getTimeStamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                .withLocale(Locale.US);

        Timestamp oldfashionedTimestamp = this.timeStamp;

        ZonedDateTime dateTime = oldfashionedTimestamp.toInstant()
                .atZone(ZoneId.systemDefault());
        String desiredFormat = dateTime.format(formatter);
        return desiredFormat;
    }
    //@CreationTimestamp
    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

//    public void addToUserLikedPosts(Post post) {
//        this.userLikedPosts.add(post);
//    }
//
//    public void removeFromUserLikedPosts(Post post) {
//        this.userLikedPosts.remove(post);
//    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    public void setSaved(List<Post> saved) {
        this.saved = saved;
    }

    public void setGallery(List<Post> gallery) {
        this.gallery = gallery;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public void setUserLikedPosts(List<Post> userLikedPosts) {
        this.userLikedPosts = userLikedPosts;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void addToBio(String text) {
       this.bio= this.bio+text;
    }


    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", firebaseUid='" + firebaseUid + '\'' +
                ", saved=" + saved +
                ", gallery=" + gallery +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", bio='" + bio + '\'' +
                ", profileImageURL='" + profileImageURL + '\'' +
                ", timeStamp=" + timeStamp +
                ", userLikedPosts=" + userLikedPosts +
                '}';
    }
}


