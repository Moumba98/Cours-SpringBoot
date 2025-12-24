package com.example.demo.book.service;


import ch.qos.logback.core.util.StringUtil;
import com.example.demo.book.persistence.BookRepository;
import com.example.demo.book.model.BookEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class BookService {


    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    public String createBook(String bookName, int bookPage){

        if (bookName == null) {
            System.out.println("ERREUR : Le Book ne peux pas etre nul");
            return "Erreur : Nom manquant ou mal mis ";
        }

        if (bookPage <= 3) {
            System.out.println("ERREUR : le livre est trop petit");
            return "Erreur : Trop peu de pages ou meme pas de page";
        }

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
