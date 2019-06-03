package com.example.blog.service;

public interface UpdatePostService {

    Boolean updatePostById(Long id, String newContent);
}
