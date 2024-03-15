package com.onpier.libraryapi.service;

import com.onpier.libraryapi.csv.Reader;
import com.onpier.libraryapi.mapper.MapperModel;
import com.onpier.libraryapi.model.Book;
import com.onpier.libraryapi.model.BorrowedBook;
import com.onpier.libraryapi.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class ReaderService {

    private final Reader reader;
    private final MapperModel mapperModel;

    public ReaderService(Reader reader, MapperModel mapperModel) {
        this.reader = reader;
        this.mapperModel = mapperModel;
    }

    public List<User> readUsers() {

        return readCsvFile("src/main/resources/user.csv", mapperModel::mapToUser);
    }

    public List<Book> readBooks() {

        return readCsvFile("src/main/resources/books.csv", mapperModel::mapToBook);
    }

    public List<BorrowedBook> readBorrowedBooks() {

        return readCsvFile("src/main/resources/borrowed.csv", mapperModel::mapToBorrowedBook);
    }

    private <T> List<T> readCsvFile(String cvsFile, Reader.RowMapper<T> rowMapper) {

        List<T> list = null;
        try {
            Path path = Path.of(cvsFile);

            list = reader.read(path, rowMapper);
        } catch (IOException e) {
            log.error("Error occurred while reading csv file", e);
            list = Collections.emptyList();
        }

        return list;
    }
}
