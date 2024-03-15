package com.onpier.libraryapi.csv;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReaderTest {

    private final Reader reader = new Reader();

    @Test
    void shouldReadAllRecordsInCsvFile() throws IOException, URISyntaxException {
        var path = givenCsvFilePath();
        var expectedFirstRecord = givenFirstRecord();
        var expectedSecondRecord = givenSecondRecord();

        var records = whenReadingCsvFile(path);
        var iterator = records.iterator();

        thenNumberOfReadRecordsMatchesExpectation(11L, records.size());
        thenRecordMatchesExpectation(expectedFirstRecord, iterator.next());
        thenRecordMatchesExpectation(expectedSecondRecord, iterator.next());
    }

    private Path givenCsvFilePath() throws URISyntaxException {
        return Paths.get(
                Objects.requireNonNull(this.getClass().getClassLoader().getResource("user.csv")).toURI()
        );
    }

    private Map<String, String> givenFirstRecord() {
        return Map.of(
                "Name", "Aexi",
                "First name", "Liam",
                "Member since", "01/01/2010",
                "Member till", "",
                "Gender", "m"
        );
    }

    private Map<String, String> givenSecondRecord() {
        return Map.of(
                "Name", "Zhungwang",
                "First name", "Noah",
                "Member since", "11/24/2020",
                "Member till", "",
                "Gender", "m"
        );
    }

    private List<Map<String, String>> whenReadingCsvFile(Path filePath) throws IOException {
        return reader.read(filePath, item -> item);
    }

    @SuppressWarnings("SameParameterValue")
    private void thenNumberOfReadRecordsMatchesExpectation(long expected, long numberOfRecords) {
        assertEquals(expected, numberOfRecords);
    }

    private void thenRecordMatchesExpectation(Map<String, String> expected, Map<String, String> readRecord) {
        assertEquals(expected.get("Name"), readRecord.get("Name"));
        assertEquals(expected.get("First name"), readRecord.get("First name"));
        assertEquals(expected.get("Member since"), readRecord.get("Member since"));
        assertEquals(expected.get("Member till"), readRecord.get("Member till"));
        assertEquals(expected.get("Gender"), readRecord.get("Gender"));
    }
}
