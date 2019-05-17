package com.example.blog.service.impl;

import com.example.blog.dto.Post;
import com.example.blog.dto.PostRepository;
import com.example.blog.service.SavePostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SavePostServiceImpl implements SavePostService {

    private PostRepository postRepository;

    @Override
    public void savePostToRepository(String author, String content) {
        Post post = new Post(author, content);
        postRepository.save(post);
    }
}
