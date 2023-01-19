package com.blogrestapi.demo.repository;

import com.blogrestapi.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDataRepository extends JpaRepository<Post, Long> {


}
