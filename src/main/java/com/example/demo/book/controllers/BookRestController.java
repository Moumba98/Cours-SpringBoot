package com.example.demo.book.controllers;


import com.example.demo.book.dto.BookDTO;
import com.example.demo.book.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController

public class BookRestController {

    private static final Logger log = LoggerFactory.getLogger(BookRestController.class);
    private final BookService bookService;

    public BookRestController(BookService bookService){
        this.bookService=bookService;
    }

    @GetMapping("/book")
    public String get(@RequestParam String bookName,@RequestParam int bookPage) {

        log.info(bookName);
        log.info(String.valueOf(bookPage));

        String reponse = bookService.createBook(bookName,bookPage);

     return reponse;
    }

    @PostMapping("/book")
    public String post(@RequestBody BookDTO.PostInput input) {

        log.info(input.getBookName());
        log.info(String.valueOf(input.getBookPage()));

        String reponse = bookService.createBook(
                input.getBookName(),
                input.getBookPage()
        );

        return reponse;
    }



}
