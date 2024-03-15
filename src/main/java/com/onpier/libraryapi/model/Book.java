package com.onpier.libraryapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private String title;

    private String author;

    private String genre;

    private String publisher;
}
