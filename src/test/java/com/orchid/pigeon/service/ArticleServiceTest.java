package com.orchid.pigeon.service;

import com.orchid.pigeon.config.RootConfig;
import com.orchid.pigeon.model.Article;
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
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Before
    public void before() {
        esTemplate.deleteIndex(Article.class);
        esTemplate.createIndex(Article.class);
        esTemplate.putMapping(Article.class);
        esTemplate.refresh(Article.class);
    }

    @Test
    public void testSave() {

        Article article = new Article();
        article.setId("123");
        article.setTitle("The Great Gatsby");
        article.setAuthor("F. Scott Fitzgerald");
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

        Optional<Article> testArticle = articleService.findById(article.getId());

        assertNotNull(testArticle.get().getId());
        assertEquals(testArticle.get().getTitle(), article.getTitle());
        assertEquals(testArticle.get().getAuthor(), article.getAuthor());

    }

    @Test
    public void testFindByTitle() {

        Article article = new Article();
        article.setId("123");
        article.setTitle("The Great Gatsby");
        article.setAuthor("F. Scott Fitzgerald");
        articleService.save(article);

        List<Article> byTitle = articleService.findByTitle(article.getTitle());
        assertThat(byTitle.size(), is(1));
    }

    @Test
    public void testFindByAuthor() {

        List<Article> ArticleList = new ArrayList<Article>();

        Article the_great_gatsby = new Article();
        the_great_gatsby.setId("123");
        the_great_gatsby.setTitle("The Great Gatsby");
        the_great_gatsby.setAuthor("F. Scott Fitzgerald");

        Article pride_and_prejudice = new Article();
        pride_and_prejudice.setId("769");
        pride_and_prejudice.setTitle("Pride and Prejudice");
        pride_and_prejudice.setAuthor("Jane Austen");

        ArticleList.add(the_great_gatsby);
        ArticleList.add(pride_and_prejudice);

        for (Article article : ArticleList) {
            articleService.save(article);
        }

        Page<Article> byAuthor = articleService.findByAuthor("Jane Austen", new PageRequest(0, 10));
        assertThat(byAuthor.getTotalElements(), is(1L));

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
        List<Article> Articles = articleService.findByTitle("The Great Gatsby");
        assertTrue(Articles.isEmpty());
        //Article testArticle = ArticleService.Article.getId());
        //assertNull(testArticle);
    }

}

