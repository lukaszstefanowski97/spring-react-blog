package com.example.blog.validation.impl;

import com.example.blog.validation.InputValidation;

public class InputValidationImpl implements InputValidation {

    @Override
    public boolean validateAuthor(String author) {
        return !(author == null) && author.length() > 2;
    }

    @Override
    public boolean validateContent(String content) {
        return !(content == null) && content.length() > 2;
    }
}
