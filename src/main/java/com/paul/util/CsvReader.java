package com.paul.util;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public final class CsvReader {

    public static List<String[]> readCsvBody(Reader reader, char delimiter, boolean hasHeader, boolean ignoreQuotations) {
        try {
            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(delimiter)
                    .withIgnoreQuotations(ignoreQuotations)
                    .build();

            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(hasHeader ? 1 : 0)
                    .withCSVParser(parser)
                    .build();

            return csvReader.readAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] readCsvHeader(Reader reader) {
        try (CSVReader csvReader = new CSVReader(reader)) {
            return csvReader.readNext();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

}
