package com.unknown.library.backend.controllers;

import com.unknown.library.backend.models.BookSimplest;
import com.unknown.library.backend.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/books")
@RequiredArgsConstructor
public class BookController {


    private final BookService bookService;


    @GetMapping(value = "/{id}")
    BookSimplest getSimple(@PathVariable Long id) {
        return bookService.findSimplest(id);
    }

}
