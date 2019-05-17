package com.example.blog.service;

import com.example.blog.dto.Post;

import java.util.List;
import java.util.Optional;

public interface GetPostService {

    List<Post> getAllPosts();

    Optional<Post> getPostById(Long id);
}
