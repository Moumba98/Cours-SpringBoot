package com.example.demo.book.controllers;


import com.example.demo.book.dto.BookDTO;
import com.example.demo.book.model.BookEntity;
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
    public java.util.List<BookEntity> get() {
        log.info("Demande de récupération de tous les livres");
        // On appelle le service qui nous donne la liste
        java.util.List<BookEntity> listeLivres = bookService.getAllBooks();

        return listeLivres;
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

    @GetMapping("/book/{name}")
    public Object getOne(@PathVariable String name) {
        log.info("Tentative de récupération du livre : " + name);

        return bookService.getBookByTitle(name);
    }

    @PutMapping("/book/{id}")
    public String update(@PathVariable long id, @RequestBody BookDTO.PostInput input) {
        log.info("Mise à jour du livre ID : " + id);

        // On récupère les infos depuis l'objet "input" (le JSON)
        return bookService.updateBook(id, input.getBookName(), input.getBookPage());
    }

    @DeleteMapping("/book/{id}")
    public String delete(@PathVariable long id) {
        log.info("Demande de suppression du livre ID : " + id);
        return bookService.deleteBook(id);
    }


}
