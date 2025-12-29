package com.example.demo.book.persistence;

import com.example.demo.book.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository


public interface BookRepository extends JpaRepository<BookEntity,Long> {

    public BookEntity findByName(String name);

    public BookEntity findByNameAndPage(String name, int page);

}
