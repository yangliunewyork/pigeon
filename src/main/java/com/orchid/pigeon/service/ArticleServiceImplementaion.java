package com.orchid.pigeon.service;

import com.orchid.pigeon.model.Article;
import com.orchid.pigeon.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class ArticleServiceImplementaion {

    @Autowired
    private ArticleRepository articleRepository;

    public void save(Article article) {
        articleRepository.save(article);
    }

    public void delete(Article article) {
        articleRepository.delete(article);
    }

    public Article findOne(Integer id) {
        return articleRepository.findOne(id);
    }

    public Iterable<Article> findAll() {
        return articleRepository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "id")));
    }

    public Page<Article> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    public List<Article> findByTitle(String title) {
        return articleRepository.findByTitle(title);
    }

    public Page<Article> findByTitle(String title, Pageable pageable) {
        return articleRepository.findByTitle(title, pageable);
    }
}
