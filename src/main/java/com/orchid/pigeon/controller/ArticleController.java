package com.orchid.pigeon.controller;


import com.orchid.pigeon.model.Article;
import com.orchid.pigeon.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(value="/articles", method = GET)
    public ModelAndView showArticleList() {
        Iterable<Article> articleList = articleService.findAll();
        ArrayList<Article> articles = new ArrayList<Article>();
        for (Article article : articleList) {
            articles.add(article);
        }

        ModelAndView modelAndView = new ModelAndView("Articles/ArticleList");
        modelAndView.addObject("articles", articles);
        return modelAndView;
    }

    @RequestMapping(value="/articles/new", method = GET)
    public ModelAndView getArticleForm(ModelAndView modelAndView) {
        Article article = new Article();
        modelAndView.addObject("article", article);
        modelAndView.setViewName("Articles/ArticleForm");
        return modelAndView;
    }

    @RequestMapping(value="/articles/add", method = POST)
    public String saveArticle(@ModelAttribute Article article) {
        article.setPublishDate(new Date());
        Article my_article = articleService.save(article);
        return "redirect:/articles/" + my_article.getId();
    }

    @RequestMapping(value="/articles/{articleId}", method = DELETE)
    public String deleteArticle(@PathVariable("articleId") String articleId) {
        Optional<Article> articleOptional = articleService.findById(articleId);
        if (articleOptional.isPresent()) {
            articleService.delete(articleOptional.get());
        }
        return "redirect:/articles";
    }

    @RequestMapping(value="/articles/{articleId}", method = GET)
    public ModelAndView showArticle(@PathVariable("articleId") String articleId,
                                    ModelAndView modelAndView) {
        Optional<Article> articleOptional = articleService.findById(articleId);
        if (articleOptional.isPresent()) {
            modelAndView.addObject("article", articleOptional.get());
            modelAndView.setViewName("Articles/ShowArticle");
        }
        return modelAndView;
    }
}

