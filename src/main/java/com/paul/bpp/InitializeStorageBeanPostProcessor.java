package com.paul.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.Arrays;

public class InitializeStorageBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        Arrays.stream(bean.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(InitializeStorage.class))
                .forEach(field -> {
                    InitializeStorage initializeStorage = field.getAnnotation(InitializeStorage.class);
                    String filePath = initializeStorage.value();
                    field.setAccessible(true);
                    try {
                        // Here you would add logic to read from the file and initialize the field
                        // For demonstration, we'll just print the file path
                        System.out.println("Initializing field: " + field.getName() + " from file: " + filePath);
                        // Example: field.set(bean, yourLoadedData);
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to initialize storage for field: " + field.getName(), e);
                    }
                });

        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }
}
