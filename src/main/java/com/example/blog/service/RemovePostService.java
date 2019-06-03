package com.example.blog.service;

public interface RemovePostService {

    void removeAllPosts();

    Boolean removePostById(Long id);
}
