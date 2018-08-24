package com.orchid.pigeon.service;

import com.orchid.pigeon.model.Article;
import com.orchid.pigeon.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImplementaion  implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Article save(Article article) {
        return articleRepository.save(article);
    }

    public void delete(Article article) {

        articleRepository.delete(article);
    }

    @Override
    public Optional<Article> findById(String id) {

        return articleRepository.findById(id);
    }

    @Override
    public Page<Article> findByAuthor(String author, PageRequest pageRequest) {

        return articleRepository.findByAuthor(author, pageRequest);
    }


    public Iterable<Article> findAll() {
        return articleRepository.findAll();
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
