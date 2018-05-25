package com.orchid.pigeon.repository;

import com.orchid.pigeon.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


import java.util.List;


public interface BookRepository extends ElasticsearchRepository<Book, String> {

    //Book findOne(String id);

    Page<Book> findByAuthor(String author, Pageable pageable);

    List<Book> findByTitle(String title);
}