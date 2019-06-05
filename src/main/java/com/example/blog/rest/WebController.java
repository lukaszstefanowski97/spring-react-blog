package com.example.blog.rest;

import com.example.blog.config.Messages;
import com.example.blog.dto.Post;
import com.example.blog.service.GetPostService;
import com.example.blog.service.RemovePostService;
import com.example.blog.service.SavePostService;
import com.example.blog.service.UpdatePostService;
import com.example.blog.validation.InputValidation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.example.blog.config.DataWorkflow.entries;
import static com.example.blog.config.Messages.*;

@Controller
@Slf4j
@AllArgsConstructor
public class WebController {

    @Autowired
    InputValidation inputValidation;

    @Autowired
    SavePostService savePostService;

    @Autowired
    GetPostService getPostService;

    @Autowired
    UpdatePostService updatePostService;

    @Autowired
    RemovePostService removePostService;

    @RequestMapping("/")
    public String getAllPosts(Model model) {
        log.info("Getting list of posts...");
        model.addAttribute("helloMessage", Messages.HELLO_MESSAGE);
        model.addAttribute("noContent", Messages.NO_RECORDS);
        model.addAttribute("posts", getPostService.getAllPosts());
        model.addAttribute("post", new Post());
        return "posts";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deletePosts/{id}")
    public String deletePostById(@PathVariable Long id, Model model) {
        if (removePostService.removePostById(id)) {
            --entries;
            model.addAttribute("helloMessage", Messages.HELLO_MESSAGE);
            model.addAttribute("noContent", Messages.NO_RECORDS);
            model.addAttribute("posts", getPostService.getAllPosts());
            model.addAttribute("post", new Post());
            return "posts";
        } else {
            return "posts";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/savePost")
    public String savePost(Post post, Model model) {
        String message = "";
        if (!inputValidation.validateAuthor(post.getAuthor()) || !inputValidation.validateContent(post.getContent())) {
            model.addAttribute("message", INVALID_INPUT);
            model.addAttribute("helloMessage", Messages.HELLO_MESSAGE);
            model.addAttribute("noContent", Messages.NO_RECORDS);
            return "posts";
        }
        ++entries;
        message = "Saved new item to repository:\n\nAuthor: " + post.getAuthor() + "\nContent: " + post.getContent();
        log.info(message);
        model.addAttribute("message", POST_SAVED);
        savePostService.savePostToRepository(post.getAuthor(), post.getContent());
        model.addAttribute("posts", getPostService.getAllPosts());
        model.addAttribute("helloMessage", Messages.HELLO_MESSAGE);
        model.addAttribute("noContent", Messages.NO_RECORDS);
        return "posts";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updatePosts")
    public String updatePost(String content, Long id, Model model) {
        model.addAttribute("message", POST_SAVED);
        model.addAttribute("helloMessage", Messages.HELLO_MESSAGE);
        model.addAttribute("noContent", Messages.NO_RECORDS);
        model.addAttribute("post", new Post());
        if (updatePostService.updatePostById(id, content)) {
            updatePostService.updatePostById(id, content);
            model.addAttribute("posts", getPostService.getAllPosts());
            model.addAttribute("updateMessage", POST_UPDATED);
            return "posts";
        }
        model.addAttribute("updateMessage", POST_NOT_FOUND);
        model.addAttribute("post", new Post());
        model.addAttribute("posts", getPostService.getAllPosts());
        return "posts";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteAll")
    public String removeAll(Model model) {
        model.addAttribute("message", POST_SAVED);
        model.addAttribute("helloMessage", Messages.HELLO_MESSAGE);
        model.addAttribute("noContent", Messages.NO_RECORDS);
        model.addAttribute("post", new Post());
        removePostService.removeAllPosts();
        model.addAttribute("posts", getPostService.getAllPosts());
        return "posts";
    }
}
