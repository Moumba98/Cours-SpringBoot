package com.example.demo.book.service;


import com.example.demo.book.persistence.BookRepository;
import com.example.demo.book.model.BookEntity;
import org.springframework.stereotype.Service;

@Service
public class BookService {


    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    public String createBook(String bookName, int bookPage){



        BookEntity existingBook = bookRepository.findByNameAndPage(bookName, bookPage);

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
