package com.orchid.pigeon.controller;

import com.orchid.pigeon.service.ArticleService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


public class ArticleControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        ArticleService articleService = Mockito.mock(ArticleService.class);
        ArticleController controller = new ArticleController(articleService);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void testArticleListPage() throws Exception {
        mockMvc.perform(get("/articles"))
                .andExpect(view().name("Articles/ArticleList"));
    }

}
