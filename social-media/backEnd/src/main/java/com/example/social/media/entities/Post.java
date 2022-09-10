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
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private int likes = 0;

    @CreationTimestamp
    private Timestamp timeStamp;

    private String userFirebaseUid;
    private Long userId;

    @OneToMany
    private List<Comment> comments;

    private String username ="";
    private String postImageURL ="";
    private String profileImageURL ="";
    private String caption="";


    public Post() {
    }

    public String getTimeStamp(){

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                .withLocale(Locale.US);

        Timestamp oldfashionedTimestamp = this.timeStamp;

        ZonedDateTime dateTime = oldfashionedTimestamp.toInstant()
                .atZone(ZoneId.systemDefault());
        String desiredFormat = dateTime.format(formatter);
        return desiredFormat;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }
//
    public void deleteComment(Comment comment){
        comments.remove(comment);
    }
    public List<Comment> getComments() {
        return comments;
    }

//    public Comment findCommentById(Long commentId){
//        for(Comment comment:comments){
//            if(comment.getId().equals(commentId)){
//                return comment;
//            }
//        }
//        return null;
//    }
//
    public void setComments(List<Comment> comments) {
        this.comments = comments;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserFirebaseUid() {
        return userFirebaseUid;
    }

    public void setUserFirebaseUid(String userFirebaseUid) {
        this.userFirebaseUid = userFirebaseUid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setId(Long id) {
        Id = id;
    }


    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public Long getId() {
        return Id;
    }


    //    public List<User> getUsersThatLikedPost() {
//        return usersThatLikedPost;
//    }
//
//    public void setUsersThatLikedPost(List<User> usersThatLikedPost) {
//        this.usersThatLikedPost = usersThatLikedPost;
//    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getPostImageURL() {
        return postImageURL;
    }

    public void setPostImageURL(String postImageURL) {
        this.postImageURL = postImageURL;
    }

    public int getLikes() {
        return likes;
    }

    public void likePost(int likes) {
        this.likes += likes;
    }
    public void unLikePost(int likes){
        this.likes -= likes;
    }

    @Override
    public String toString() {
        return "Post{" +
                "Id=" + Id +
                ", likes=" + likes +
                ", timeStamp=" + timeStamp +
                ", userFirebaseUid='" + userFirebaseUid + '\'' +
                ", userId=" + userId +
                ", comments=" + comments +
                ", username='" + username + '\'' +
                ", postURL='" + postImageURL + '\'' +
                ", profileImageURL='" + profileImageURL + '\'' +
                ", caption='" + caption + '\'' +
                '}';
    }
}
