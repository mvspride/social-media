package com.example.social.media.repos;

import com.example.social.media.entities.Comment;
import com.example.social.media.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
@Repository
public interface PostRepo extends JpaRepository<Post,Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Post p SET p.caption = ?1 WHERE p.id = ?2")
    int updatePost(String caption, Long id);
}