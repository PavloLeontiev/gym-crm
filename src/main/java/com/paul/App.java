package com.paul;

import com.opencsv.exceptions.CsvException;
import com.paul.config.ApplicationConfiguration;
import com.paul.initializer.TrainerInitializer;
import com.paul.model.Trainee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;


public class App {

    public static void main(String[] args) throws IOException, CsvException, ClassNotFoundException {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        TrainerInitializer bean = annotationConfigApplicationContext.getBean(TrainerInitializer.class);

        System.out.println(bean);

    }
}
