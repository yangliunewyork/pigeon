package com.orchid.pigeon.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


public class ArticleControllerTest {
    @Test
    public void testArticleListPage() throws Exception {
        ArticleController controller = new ArticleController();
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/articles"))
                .andExpect(view().name("Articles/ArticleList"));
    }
}
