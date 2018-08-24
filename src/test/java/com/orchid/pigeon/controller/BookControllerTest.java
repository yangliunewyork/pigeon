package com.orchid.pigeon.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class BookControllerTest {
    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        BookController controller = new BookController();
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void testBookPage() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(view().name("Books/BookList"));
    }
}

