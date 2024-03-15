package com.onpier.libraryapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowedBook {

    private String borrower;
    private String book;
    private LocalDate borrowedFrom;
    private LocalDate borrowedTo;
}
