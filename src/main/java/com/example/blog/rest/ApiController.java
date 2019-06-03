package com.example.blog.rest;

import com.example.blog.dto.Post;
import com.example.blog.dto.ResponseObject;
import com.example.blog.service.GetPostService;
import com.example.blog.service.RemovePostService;
import com.example.blog.service.SavePostService;
import com.example.blog.service.UpdatePostService;
import com.example.blog.validation.InputValidation;
import com.example.blog.validation.impl.InputValidationImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.example.blog.config.DataWorkflow.entries;
import static com.example.blog.config.Messages.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@Slf4j
public class ApiController {

    private InputValidation inputValidation = new InputValidationImpl();

    @Autowired
    GetPostService getPostService;

    @Autowired
    SavePostService savePostService;

    @Autowired
    UpdatePostService updatePostService;

    @Autowired
    RemovePostService removePostService;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/getPosts")
    public <T> T getAllPosts() {
        log.info("Getting list of posts...");

        if (entries == 0) {
            ResponseObject responseObject =
                    new ResponseObject(204, STATUS_NO_CONTENT, NO_RECORDS);

            return (T) responseObject;
        }
        return (T) getPostService.getAllPosts();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/getPosts/{id}")
    public Optional<Post> getPostById(@PathVariable Long id) {
        Optional<Post> post = getPostService.getPostById(id);
        return post;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.POST, value = "/savePost")
    public ResponseObject savePost(@RequestBody Post post) {
        String message = "";
        if (!inputValidation.validateAuthor(post.getAuthor()) || !inputValidation.validateContent(post.getContent())) {
            if (!inputValidation.validateContent(post.getContent())) {
                message += INVALID_CONTENT_MESSAGE;
            }
            if (!inputValidation.validateAuthor(post.getAuthor())) {
                message += INVALID_AUTHOR_MESSAGE;
            }
            return new ResponseObject(400, STATUS_BAD_REQUEST, message);
        }
        ++entries;
        message = "Saved new item to repository:\n\nAuthor: " + post.getAuthor() + "\nContent: " + post.getContent();
        log.info(message);
        savePostService.savePostToRepository(post.getAuthor(), post.getContent());
        return new ResponseObject(200, STATUS_OK, POST_SAVED);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.PUT, value = "/updatePost/{id}")
    public ResponseObject updatePost(@RequestBody Post post, @PathVariable Long id) {
        if (updatePostService.updatePostById(id, post.getContent())) {
            log.info("Post with id: " + id + "hase been updated.\n\n" + "Author: " + post.getAuthor()
                    + "\nContent: " + post.getContent());
            return new ResponseObject(200, STATUS_OK, POST_UPDATED);
        }
        return new ResponseObject(404, STATUS_NOT_FOUND, POST_NOT_FOUND);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.DELETE, value = "/deletePosts")
    public ResponseObject deleteAllPosts() {
        removePostService.removeAllPosts();
        return new ResponseObject(200, STATUS_OK, POSTS_REMOVED);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.DELETE, value = "/deletePosts/{id}")
    public ResponseObject deletePostById(@PathVariable Long id) {
        if (removePostService.removePostById(id)) {
            return new ResponseObject(200, STATUS_OK, POST_REMOVED);
        } else {
            return new ResponseObject(404, STATUS_NOT_FOUND, POST_NOT_FOUND);
        }
    }
}
