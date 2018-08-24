package com.orchid.pigeon.service;


import com.orchid.pigeon.config.RootConfig;
import com.orchid.pigeon.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    classes = {
            RootConfig.class
    }
)
@WebAppConfiguration // Important
public class BookServiceTest {

    private BookService bookService;


    @Before
    public void before() {
        bookService = mock(BookService.class);
    }

    @Test
    public void testSave() {

        Book book = new Book();
        book.setId("123");
        book.setTitle("The Great Gatsby");
        book.setAuthor("F. Scott Fitzgerald");
        when(bookService.save(book)).thenReturn(book);
        Book testBook = bookService.save(book);
        assertNotNull(testBook.getId());
        assertEquals(testBook.getTitle(), book.getTitle());
        assertEquals(testBook.getAuthor(), book.getAuthor());

    }

    @Test
    public void testFindOne() {

        Book book = new Book();
        book.setId("123");
        book.setTitle("The Great Gatsby");
        book.setAuthor("F. Scott Fitzgerald");
        bookService.save(book);

        when(bookService.findById("123")).thenReturn(Optional.of(book));
        Optional<Book> testBook = bookService.findById(book.getId());

        assertNotNull(testBook.get().getId());
        assertEquals(testBook.get().getTitle(), book.getTitle());
        assertEquals(testBook.get().getAuthor(), book.getAuthor());

    }

    @Test
    public void testFindByTitle() {

        Book book = new Book();
        book.setId("123");
        book.setTitle("The Great Gatsby");
        book.setAuthor("F. Scott Fitzgerald");
        bookService.save(book);

        List<Book> mockList = new ArrayList<Book>();
        mockList.add(book);
        when(bookService.findByTitle("The Great Gatsby"))
                .thenReturn(mockList);
        List<Book> byTitle = bookService.findByTitle(book.getTitle());
        assertThat(byTitle.size(), is(1));
    }

    @Test
    public void testFindByAuthor() {

        List<Book> bookList = new ArrayList<Book>();

        Book the_great_gatsby = new Book();
        the_great_gatsby.setId("123");
        the_great_gatsby.setTitle("The Great Gatsby");
        the_great_gatsby.setAuthor("F. Scott Fitzgerald");

        Book pride_and_prejudice = new Book();
        pride_and_prejudice.setId("769");
        pride_and_prejudice.setTitle("Pride and Prejudice");
        pride_and_prejudice.setAuthor("Jane Austen");

        bookList.add(the_great_gatsby);
        bookList.add(pride_and_prejudice);

        for (Book book : bookList) {
            bookService.save(book);
        }

        List<Book> janeAustenList = new ArrayList<Book>();
        janeAustenList.add(pride_and_prejudice);
        Page<Book> mockPageOne = new PageImpl<Book>(janeAustenList);
        when(bookService.findByAuthor("Jane Austen", new PageRequest(0, 10)))
                .thenReturn(mockPageOne);
        Page<Book> byAuthor = bookService.findByAuthor("Jane Austen", new PageRequest(0, 10));
        assertThat(byAuthor.getTotalElements(), is(1L));

        List<Book> fitzgeraldList = new ArrayList<Book>();
        fitzgeraldList.add(pride_and_prejudice);
        Page<Book> mockPageOTwo = new PageImpl<Book>(fitzgeraldList);
        when(bookService.findByAuthor("F. Scott Fitzgerald", new PageRequest(0, 10)))
                .thenReturn(mockPageOne);
        Page<Book> byAuthor2 = bookService.findByAuthor("F. Scott Fitzgerald", new PageRequest(0, 10));
        assertThat(byAuthor2.getTotalElements(), is(1L));

    }

    @Test
    public void testDelete() {

        Book book = new Book();
        book.setId("123");
        book.setTitle("The Great Gatsby");
        book.setAuthor("F. Scott Fitzgerald");
        bookService.save(book);
        bookService.delete(book);

        when(bookService.findByTitle("The Great Gatsby")).thenReturn(new ArrayList<Book>());
        List<Book> books = bookService.findByTitle("The Great Gatsby");
        assertTrue(books.isEmpty());
    }

}
