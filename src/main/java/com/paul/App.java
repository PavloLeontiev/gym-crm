package com.paul;

import com.paul.config.ApplicationConfiguration;
import com.paul.model.TrainingType;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        System.out.println(annotationConfigApplicationContext.getBean("app"));

        TrainingType fitness = TrainingType.FITNESS;

        TrainingType.valueOf(fitness.name());
    }
}
