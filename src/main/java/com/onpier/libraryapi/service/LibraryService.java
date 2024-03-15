package com.onpier.libraryapi.service;

import com.onpier.libraryapi.model.Book;
import com.onpier.libraryapi.model.BorrowedBook;
import com.onpier.libraryapi.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LibraryService {

    private final ReaderService readerService;

    public LibraryService(ReaderService readerService) {
        this.readerService = readerService;
    }

    public List<User> getBorrowedUsers() {

        List<String> borrowedUserNames = readerService.readBorrowedBooks().stream()
                .map(BorrowedBook::getBorrower)
                .toList();

        return readerService.readUsers()
                .stream()
                .filter(user -> borrowedUserNames.contains(new StringBuilder().append(user.getName()).append(",").append(user.getFirstName()).toString()))
                .toList();

    }

    public List<User> getNonTerminatedUsersNotBorrowing() {
        List<String> borrowedUserNames = readerService.readBorrowedBooks().stream()
                .map(BorrowedBook::getBorrower)
                .toList();

        return readerService.readUsers()
                .stream()
                .filter(user -> user.getMemberTill() == null && !borrowedUserNames.contains(new StringBuilder().append(user.getName()).append(",").append(user.getFirstName()).toString()))
                .collect(Collectors.toList());
    }

    public List<User> getUsersBorrowedOnDate(LocalDate targetDate) {
        List<String> borrowedUserNames = readerService.readBorrowedBooks().stream()
                .filter(book -> book.getBorrowedFrom().equals(targetDate))
                .map(BorrowedBook::getBorrower)
                .toList();

        return readerService.readUsers()
                .stream()
                .filter(user -> borrowedUserNames.contains(new StringBuilder().append(user.getName()).append(",").append(user.getFirstName()).toString()))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksBorrowedByUserInDateRange(String userName, LocalDate startDate, LocalDate endDate) {

        List<String> borrowedBooks = readerService.readBorrowedBooks().stream()
                .filter(book -> book.getBorrower().equalsIgnoreCase(userName)
                        && !book.getBorrowedFrom().isBefore(startDate)
                        && !book.getBorrowedTo().isAfter(endDate))
                .map(BorrowedBook::getBook)
                .toList();

        return readerService.readBooks()
                .stream()
                .filter(book -> book.getTitle() != null && borrowedBooks.contains(book.getTitle()))
                .collect(Collectors.toList());
    }

    public List<Book> getAvailableBooks() {
        List<String> borrowedBooks = readerService.readBorrowedBooks().stream()
                .map(BorrowedBook::getBook)
                .toList();

        return readerService.readBooks()
                .stream()
                .filter(book -> book.getTitle() != null && !borrowedBooks.contains(book.getTitle()))
                .collect(Collectors.toList());
    }
}
