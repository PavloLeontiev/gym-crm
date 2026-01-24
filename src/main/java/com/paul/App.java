package com.paul;

import com.paul.config.ApplicationConfiguration;

import com.paul.storage.impl.InMemoryStorage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        InMemoryStorage bean = annotationConfigApplicationContext.getBean(InMemoryStorage.class);
    }
}
