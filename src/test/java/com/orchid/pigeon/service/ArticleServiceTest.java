package com.orchid.pigeon.service;

import com.orchid.pigeon.config.RootConfig;
import com.orchid.pigeon.model.Article;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
public class ArticleServiceTest {


    private ArticleService articleService;


    @Before
    public void before() {
        articleService = mock(ArticleService.class);
    }

    @Test
    public void testSave() {

        Article article = new Article();
        article.setId("123");
        article.setTitle("The Great Gatsby");
        article.setAuthor("F. Scott Fitzgerald");

        when(articleService.save(article)).thenReturn(article);
        Article testArticle = articleService.save(article);

        assertNotNull(testArticle.getId());
        assertEquals(testArticle.getTitle(), article.getTitle());
        assertEquals(testArticle.getAuthor(), article.getAuthor());

    }

    @Test
    public void testFindOne() {

        Article article = new Article();
        article.setId("123");
        article.setTitle("The Great Gatsby");
        article.setAuthor("F. Scott Fitzgerald");
        articleService.save(article);

        when(articleService.findById("123")).thenReturn(Optional.of(article));
        Article testArticle = articleService.findById(article.getId()).get();

        assertNotNull(testArticle.getId());
        assertEquals(testArticle.getTitle(), article.getTitle());
        assertEquals(testArticle.getAuthor(), article.getAuthor());

    }

    @Test
    public void testFindByTitle() {

        Article article = new Article();
        article.setId("123");
        article.setTitle("The Great Gatsby");
        article.setAuthor("F. Scott Fitzgerald");
        articleService.save(article);

        List<Article> mockList = new ArrayList<Article>();
        mockList.add(article);

        when(articleService.findByTitle("The Great Gatsby")).thenReturn(mockList);

        List<Article> byTitle = articleService.findByTitle(article.getTitle());
        assertThat(byTitle.size(), is(1));
    }

    @Test
    public void testFindByAuthor() {

        List<Article> articleList = new ArrayList<Article>();

        Article the_great_gatsby = new Article();
        the_great_gatsby.setId("123");
        the_great_gatsby.setTitle("The Great Gatsby");
        the_great_gatsby.setAuthor("F. Scott Fitzgerald");

        Article pride_and_prejudice = new Article();
        pride_and_prejudice.setId("769");
        pride_and_prejudice.setTitle("Pride and Prejudice");
        pride_and_prejudice.setAuthor("Jane Austen");

        articleList.add(the_great_gatsby);
        articleList.add(pride_and_prejudice);

        for (Article article : articleList) {
            articleService.save(article);
        }

        List<Article> janeAustenList = new ArrayList<Article>();
        janeAustenList.add(pride_and_prejudice);
        Page<Article> mockPageOne = new PageImpl<Article>(janeAustenList);
        when(articleService.findByAuthor("Jane Austen", new PageRequest(0, 10)))
                .thenReturn(mockPageOne);

        Page<Article> byAuthor = articleService.findByAuthor("Jane Austen", new PageRequest(0, 10));
        assertThat(byAuthor.getTotalElements(), is(1L));


        List<Article> fitzgeraldList = new ArrayList<Article>();
        fitzgeraldList.add(the_great_gatsby);
        Page<Article> mockPageTwo = new PageImpl<Article>(fitzgeraldList);
        when(articleService.findByAuthor("F. Scott Fitzgerald", new PageRequest(0, 10)))
                .thenReturn(mockPageTwo);
        Page<Article> byAuthor2 = articleService.findByAuthor("F. Scott Fitzgerald", new PageRequest(0, 10));
        assertThat(byAuthor2.getTotalElements(), is(1L));

    }

    @Test
    public void testDelete() {

        Article article = new Article();
        article.setId("123");
        article.setTitle("The Great Gatsby");
        article.setAuthor("F. Scott Fitzgerald");
        articleService.save(article);
        articleService.delete(article);

        when(articleService.findByTitle("The Great Gatsby")).thenReturn(new ArrayList<Article>());
        List<Article> Articles = articleService.findByTitle("The Great Gatsby");
        assertTrue(Articles.isEmpty());

    }

}

