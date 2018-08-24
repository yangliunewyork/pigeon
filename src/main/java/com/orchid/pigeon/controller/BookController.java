package com.orchid.pigeon.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class BookController {


    @RequestMapping(value="/books", method = GET)
    public String books() {

        return "Books/BookList";
    }

}

