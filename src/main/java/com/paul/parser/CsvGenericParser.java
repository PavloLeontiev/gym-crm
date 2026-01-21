package com.paul.parser;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class CsvGenericParser {

    public <T> List<T> parse(String path, Class<T> type) {
        try (Reader reader = Files.newBufferedReader(Paths.get(path))) {
            return new CsvToBeanBuilder<T>(reader)
                    .withType(type)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
        } catch (IOException e) {
            throw new RuntimeException("CSV parse failed: " + path, e);
        }
    }
}

