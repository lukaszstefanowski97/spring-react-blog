package com.example.blog.service.impl;

import com.example.blog.dto.PostRepository;
import com.example.blog.service.RemovePostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RemovePostServiceImpl implements RemovePostService {

    private PostRepository postRepository;

    @Override
    public void removeAllPosts() {
        postRepository.deleteAll();
    }

    @Override
    public void removePostById(Long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
        }
    }
}
