package com.example.social.media.repos;

import com.example.social.media.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LikedPostsRepo extends JpaRepository<Post,Long> {

}
