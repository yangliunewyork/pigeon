package com.orchid.pigeon.model;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;
import org.springframework.data.elasticsearch.annotations.Document;

@Component
@Document(
        indexName = "content",
        type = "book"
)
public class Book {
    @Id
    private String id;

    private String title;

    private String content;

    private String author;

    public String getId() {

        return this.id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getTitle() {

        return this.title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public String getAuthor() {

        return this.author;
    }

    public void setAuthor(String author) {

        this.author = author;
    }

}
