package com.paul;

import com.opencsv.bean.CsvToBeanBuilder;
import com.paul.config.ApplicationConfiguration;
import com.paul.model.Trainer;
import com.paul.model.TrainingType;
import com.paul.storage.TrainerStorage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        TrainerStorage bean = annotationConfigApplicationContext.getBean(TrainerStorage.class);

        String path = bean.getInitializeFilePath();

        try (Reader reader = Files.newBufferedReader(Path.of(path))) {
            List<Trainer> parse = new CsvToBeanBuilder<Trainer>(reader)
                    .withType(Trainer.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();

            parse.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException("CSV parse failed: " + path, e);
        }
    }
}
