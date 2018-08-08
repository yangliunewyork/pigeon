package com.orchid.pigeon.service;


import com.orchid.pigeon.config.RootConfig;
import com.orchid.pigeon.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    classes = {
            RootConfig.class
    }
)
@WebAppConfiguration // Important
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Before
    public void before() {
        esTemplate.deleteIndex(Book.class);
        esTemplate.createIndex(Book.class);
        esTemplate.putMapping(Book.class);
        esTemplate.refresh(Book.class);
    }

    @Test
    public void testSave() {

        Book book = new Book();
        book.setId("123");
        book.setTitle("The Great Gatsby");
        book.setAuthor("F. Scott Fitzgerald");
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

        Page<Book> byAuthor = bookService.findByAuthor("Jane Austen", new PageRequest(0, 10));
        assertThat(byAuthor.getTotalElements(), is(1L));

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
        List<Book> books = bookService.findByTitle("The Great Gatsby");
        assertTrue(books.isEmpty());
        //Book testBook = bookService.book.getId());
        //assertNull(testBook);
    }

}
