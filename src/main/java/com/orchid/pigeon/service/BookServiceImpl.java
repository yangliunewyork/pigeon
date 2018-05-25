package com.orchid.pigeon.service;

import com.orchid.pigeon.model.Book;
import com.orchid.pigeon.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {

        this.bookRepository = bookRepository;
    }

    public Book save(Book book) {

        return bookRepository.save(book);
    }

    public void delete(Book book) {

        bookRepository.delete(book);
    }

    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    public Iterable<Book> findAll() {

        return bookRepository.findAll();
    }

    public Page<Book> findByAuthor(String author, PageRequest pageRequest) {
        return bookRepository.findByAuthor(author, pageRequest);
    }

    public List<Book> findByTitle(String title) {

        return bookRepository.findByTitle(title);
    }

}