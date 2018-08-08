package com.orchid.pigeon.repository;

import com.orchid.pigeon.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BookRepository
        extends ElasticsearchRepository<Book, String> {


    Page<Book> findByAuthor(String author, Pageable pageable);

    List<Book> findByTitle(String title);
}