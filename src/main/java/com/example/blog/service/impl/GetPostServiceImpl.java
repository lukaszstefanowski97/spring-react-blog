package com.example.blog.service.impl;

import com.example.blog.dto.Post;
import com.example.blog.dto.PostRepository;
import com.example.blog.service.GetPostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class GetPostServiceImpl implements GetPostService {

    private PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }
}
