package com.onpier.libraryapi.controller;

import com.onpier.libraryapi.model.Book;
import com.onpier.libraryapi.model.User;
import com.onpier.libraryapi.service.LibraryService;
import com.onpier.libraryapi.util.DateUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/api/library")
public class LibraryController {

    private final LibraryService libraryService;
    private final DateUtil dateUtil;

    public LibraryController(LibraryService libraryService, DateUtil dateUtil) {
        this.libraryService = libraryService;
        this.dateUtil = dateUtil;
    }

    @GetMapping("/borrowedUsers")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getBorrowedUsers() {

        return libraryService.getBorrowedUsers();
    }

    @GetMapping("/nonTerminatedUsersNotBorrowing")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getNonTerminatedUsersNotBorrowing() {

        return libraryService.getNonTerminatedUsersNotBorrowing();
    }

    @GetMapping("/usersBorrowedOnDate")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsersBorrowedOnDate(@RequestParam String date) {

        LocalDate targetDate = dateUtil.parseDateToLocaleDate(date);

        return libraryService.getUsersBorrowedOnDate(targetDate);
    }

    @GetMapping("/booksBorrowedByUserInDateRange")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getBooksBorrowedByUserInDateRange(@RequestParam String userName,
                                                        @RequestParam String fromDate,
                                                        @RequestParam String toDate) {

        LocalDate startDate = dateUtil.parseDateToLocaleDate(fromDate);
        LocalDate endDate = dateUtil.parseDateToLocaleDate(toDate);

        return libraryService.getBooksBorrowedByUserInDateRange(userName, startDate, endDate);
    }

    @GetMapping("/availableBooks")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAvailableBooks() {

        return libraryService.getAvailableBooks();
    }
}
