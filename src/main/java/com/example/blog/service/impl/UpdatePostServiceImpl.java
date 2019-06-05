package com.example.blog.service.impl;

import com.example.blog.dto.Post;
import com.example.blog.dto.PostRepository;
import com.example.blog.service.UpdatePostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
public class UpdatePostServiceImpl implements UpdatePostService {

    private PostRepository postRepository;

    @Override
    public Boolean updatePostById(Long id, String newContent) {
        if (id == null) return false;
        if (postRepository.existsById(id)) {
            Post post = postRepository.getOne(id);
            postRepository.deleteById(id);

            Date date = new Date();
            post.setContent(newContent);
            post.setDateLastModified(date.toString());
            postRepository.save(post);
            return true;
        }
        return false;
    }
}
