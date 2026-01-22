package com.paul;

import com.opencsv.exceptions.CsvException;
import com.paul.config.ApplicationConfiguration;
import com.paul.storage.impl.TrainerStorage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;


public class App {

    public static void main(String[] args) throws IOException, CsvException {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        TrainerStorage bean = annotationConfigApplicationContext.getBean(TrainerStorage.class);

        System.out.println(bean);

    }
}
