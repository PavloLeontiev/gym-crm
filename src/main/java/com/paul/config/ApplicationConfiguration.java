package com.paul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.paul")
public class ApplicationConfiguration {

        @Bean(name = "app")
        public String app() {
                return "Application is running!";
        }
}
