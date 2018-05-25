package com.orchid.pigeon.service;

import com.orchid.pigeon.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book save(Book book);

    void delete(Book book);

    Optional<Book> findById(String id);

    Iterable<Book> findAll();

    Page<Book> findByAuthor(String author, PageRequest pageRequest);

    List<Book> findByTitle(String title);

}
