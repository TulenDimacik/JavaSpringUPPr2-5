package com.example.demo.repo;

import com.example.demo.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    //List<Post> findByTitleContains(String title);
    @Query("SELECT m FROM Post m WHERE m.full_text LIKE ?1%")
    List<Post> searchByRatingStartsWith(String full_text);

    //List<Post> findByTitleContains(@Param("title") double title);

}
