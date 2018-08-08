package com.orchid.pigeon.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class BookControllerTest {
    @Test
    public void testBookPage() throws Exception {
        BookController controller = new BookController();
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/books"))
                .andExpect(view().name("Books/BookList"));
    }
}

