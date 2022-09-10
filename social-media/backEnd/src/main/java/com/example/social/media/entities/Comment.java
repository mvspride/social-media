package com.example.social.media.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;


@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long Id;

    private int likeCount = 0;
    private String text ="";
    private String userName;
    private String postPath;

    public Comment() {
    }



    @ManyToOne
   public User user;

    @ManyToOne
    public Post post;


    @CreationTimestamp
    private Timestamp timeStamp;

    @CreationTimestamp
    public String getTimeStamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                .withLocale(Locale.US);

        Timestamp oldfashionedTimestamp = this.timeStamp;

        ZonedDateTime dateTime = oldfashionedTimestamp.toInstant()
                .atZone(ZoneId.systemDefault());
        String desiredFormat = dateTime.format(formatter);
        return desiredFormat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void likeComment(int likeCount) {
        this.likeCount += likeCount;
    }

    public void unLikeComment(int likeCount) {
        this.likeCount -= likeCount;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
