package com.example.blog.validation;

import org.springframework.stereotype.Component;

public interface InputValidation {

    boolean validateAuthor(String author);

    boolean validateContent(String content);
}
