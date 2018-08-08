package com.orchid.pigeon.repository;

import com.orchid.pigeon.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ArticleRepository extends
        ElasticsearchRepository<Article, String> {


    List<Article> findByTitle(String title);

    Page<Article> findByAuthor(String author, Pageable pageable);

    Page<Article> findByTitle(String title, Pageable pageable);

}