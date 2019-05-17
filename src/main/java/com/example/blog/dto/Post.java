package com.example.blog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@Slf4j
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue
    Long id;

    @JsonProperty("author")
    String author;

    @JsonProperty("content")
    String content;

    @JsonProperty("date_created")
    String dateCreated;

    @JsonProperty("date_last_modified")
    String dateLastModified;

    public Post(String author, String content) {
        this.author = author;
        this.content = content;
        Date date = new Date();
        String dateAsString = date.toString();
        this.dateCreated = dateAsString;
        this.dateLastModified = dateAsString;
    }
}
