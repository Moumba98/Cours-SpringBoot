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

    //afficher books

    public java.util.List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    // afficher par son nom


    public Object getBookByTitle(String name) {
        BookEntity book = bookRepository.findByName(name);

        if (book == null) {
            return "Le livre n'existe pas dans notre base de données.";
        }

        return book;
    }

    // modifier un book

    public String updateBook(long id, String newName, int newPageCount) {

        return bookRepository.findById(id).map(book -> {
            // 2. On modifie TOUS les champs souhaités
            book.setName(newName);
            book.setPage(newPageCount);


            bookRepository.save(book);

            return "Le livre ID " + id + " a été mis à jour : Nouveau nom '" + newName + "' et " + newPageCount + " pages.";
        }).orElse("Erreur : Aucun livre trouvé avec l'ID " + id);
    }

    // Suprimer book

    public String deleteBook(long id) {
        // 1. On vérifie si le livre existe avant de supprimer
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return "Le livre avec l'ID " + id + " a été supprimé avec succès.";
        } else {
            return "Erreur : Impossible de supprimer, aucun livre trouvé avec l'ID " + id;
        }
    }


}
