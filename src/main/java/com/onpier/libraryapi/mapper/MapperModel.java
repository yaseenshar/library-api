package com.onpier.libraryapi.mapper;

import com.onpier.libraryapi.model.Book;
import com.onpier.libraryapi.model.BorrowedBook;
import com.onpier.libraryapi.model.User;
import com.onpier.libraryapi.util.DateUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@Component
public class MapperModel {

    private final DateUtil dateUtil;

    public MapperModel(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }

    public User mapToUser(Map<String, String> row) {

        String name = row.get("Name");
        String firstName = row.get("First name");
        LocalDate memberSince = dateUtil.parseDateToLocaleDate(row.get("Member since"));
        LocalDate memberTill = dateUtil.parseDateToLocaleDate(row.get("Member till"));
        String gender = row.get("Gender");

        return new User(name, firstName, memberSince, memberTill, gender);
    }

    public Book mapToBook(Map<String, String> row) {

        String title = row.get("Title");
        String author = row.get("Author");
        String genre = row.get("Genre");
        String publisher = row.get("Publisher");

        return new Book(title, author, genre, publisher);
    }

    public BorrowedBook mapToBorrowedBook(Map<String, String> row) {

        String title = row.get("Borrower");
        String author = row.get("Book");
        LocalDate borrowedFrom = dateUtil.parseDateToLocaleDate(row.get("borrowed from"));
        LocalDate borrowedTo = dateUtil.parseDateToLocaleDate(row.get("borrowed to"));

        return new BorrowedBook(title, author, borrowedFrom, borrowedTo);
    }
}
