package com.example.blog.rest;

import com.example.blog.dto.Post;
import com.example.blog.dto.ResponseObject;
import com.example.blog.service.GetPostService;
import com.example.blog.service.RemovePostService;
import com.example.blog.service.SavePostService;
import com.example.blog.service.UpdatePostService;
import com.example.blog.validation.InputValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.example.blog.config.DataWorkflow.entries;
import static com.example.blog.config.Messages.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@Slf4j
public class RestController {

    @Autowired
    InputValidation inputValidation;

    @Autowired
    GetPostService getPostService;

    @Autowired
    SavePostService savePostService;

    @Autowired
    UpdatePostService updatePostService;

    @Autowired
    RemovePostService removePostService;

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

    @RequestMapping("/getPosts/{id}")
    public ResponseObject getPostById(@PathVariable Long id) {
        getPostService.getPostById(id);
        return new ResponseObject(200, STATUS_OK, POST_REMOVED);
    }

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
        savePostService.savePostToRepository(post.getAuthor(), post.getContent());
        return new ResponseObject(200, STATUS_OK, POST_SAVED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deletePosts")
    public ResponseObject deleteAllPosts() {
        removePostService.removeAllPosts();
        return new ResponseObject(200, STATUS_OK, POSTS_REMOVED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deletePosts/{id}")
    public ResponseObject deletePostById(@PathVariable Long id) {
        removePostService.removePostById(id);
        return new ResponseObject(200, STATUS_OK, POST_REMOVED);
    }
}
