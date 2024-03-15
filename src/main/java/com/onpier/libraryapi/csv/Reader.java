package com.onpier.libraryapi.csv;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
@Slf4j
public class Reader {

    public <T> List<T> read(Path csvFilePath, RowMapper<T> rowMapper) throws IOException {
        try (InputStream inputStream = Files.newInputStream(csvFilePath);
             var parser = CSVParser.parse(inputStream,UTF_8,CSVFormat.DEFAULT.builder()
                                .setHeader()
                                .setSkipHeaderRecord(true)
                                .setIgnoreHeaderCase(true)
                                .setTrim(true)
                                .build()
                )
        ) {
            return parser.stream()
                    .map(row -> rowMapper.apply(row.toMap()))
                    .toList();
        }
    }

    @FunctionalInterface
    public interface RowMapper<T> {
        T apply(Map<String, String> row);
    }

}
