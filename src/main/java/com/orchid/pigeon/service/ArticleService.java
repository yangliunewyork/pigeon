package com.orchid.pigeon.service;

import com.orchid.pigeon.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "content", type = "article")
public interface ArticleService {
    public void save(Article article);

    public void delete(Article article);

    public Article findOne(Integer id);

    public Iterable<Article> findAll();

    public Page<Article> findAll(Pageable pageable);

    public List<Article> findByTitle(String title);

    public Page<Article> findByTitle(String title, Pageable pageable);
}
