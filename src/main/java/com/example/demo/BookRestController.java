package com.example.demo;


import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class BookRestController {

    private final BookRepository bookRepository;

    public BookRestController(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    @GetMapping("/book")
    public String get(@RequestParam String bookName,@RequestParam int bookPage) {

        BookEntity existingBook = bookRepository.FindByNameAndPage(bookName, bookPage);

        if (existingBook == null){


            BookEntity newBook = BookEntity.builder()
                    .name(bookName)
                    .page(bookPage)
                    .build();

            System.out.println(newBook);
            bookRepository.save(newBook);

            return "le livre a bien éte creer";
        } else {

            return "le livre existe déja";
        }



    }


}
