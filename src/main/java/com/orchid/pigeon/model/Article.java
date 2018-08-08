package com.orchid.pigeon.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.stereotype.Component;

@Component
@Document(
        indexName = "content",
        type = "article"
)
public class Article {
    static final String CHINESE_ANALYZER = "ik";

    @Id
    private String id;

    private String title;

    private String content;

    private String author;

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getContent() {

        return content;
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

    @Override
    public String toString() {
        return "Article [id=" + id + ", title=" + title + ", content="
                + content + "]";
    }

}