package com.orchid.pigeon.model;

import org.elasticsearch.search.DocValueFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Document(
        indexName = "chinese_article",
        type = "article"
)
public class Article {
    static final String CHINESE_ANALYZER = "smartcn";

    @Id
    private String id;

    private String title;

    private String content;

    private String quillContent;

    private String author;

    private Date publishDate;

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

    public String getQuillContent() {
        return this.quillContent;
    }

    public void setQuillContent(String quillContent) {
        this.quillContent = quillContent;
    }

    public String getAuthor() {

        return this.author;
    }

    public void setAuthor(String author) {

        this.author = author;
    }

    public Date getPublishDate() {

        return this.publishDate;
    }

    public void setPublishDate(Date date) {

        this.publishDate = date;
    }

    @Override
    public String toString() {
        return "Article [id=" + id + ", title=" + title + ", content="
                + content + "]";
    }

}