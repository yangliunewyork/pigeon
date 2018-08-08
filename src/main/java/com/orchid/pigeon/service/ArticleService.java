package com.orchid.pigeon.service;

import com.orchid.pigeon.model.Article;
import com.orchid.pigeon.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;
import java.util.Optional;

@Document(indexName = "content", type = "article")
public interface ArticleService {

     Article save(Article article);

     void delete(Article article);

     Optional<Article> findById(String id);

     Page<Article> findByAuthor(String author, PageRequest pageRequest);

     Iterable<Article> findAll();

     Page<Article> findAll(Pageable pageable);

     List<Article> findByTitle(String title);

     Page<Article> findByTitle(String title, Pageable pageable);
}
