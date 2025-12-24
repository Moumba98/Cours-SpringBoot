package com.example.demo.book.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class BookDTO {
    @AllArgsConstructor
    @Builder
    @Data
    @NoArgsConstructor
    public static class PostInput {


        String bookName;
        int bookPage;

    }

    public static class PostOutput{

    }
}
