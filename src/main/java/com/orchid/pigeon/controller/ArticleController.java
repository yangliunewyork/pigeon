package com.orchid.pigeon.controller;


import com.orchid.pigeon.model.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ArticleController {


    @RequestMapping(value="/articles", method = GET)
    public String books() {

        return "Articles/ArticleList";
    }

    @RequestMapping(value="/articles/new", method = GET)
    public ModelAndView getArticleForm(ModelAndView model) {
        Article article = new Article();
        model.addObject("article", article);
        model.setViewName("Articles/ArticleForm");
        return model;
    }

    @RequestMapping(value="/articles", method = POST)
    public ModelAndView saveArticle(ModelAndView model) {
        Article article = new Article();
        model.addObject("article", article);
        model.setViewName("Articles/ArticleForm");
        return model;
    }
}

