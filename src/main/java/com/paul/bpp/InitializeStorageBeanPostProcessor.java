package com.paul.bpp;

import com.paul.initializer.InMemoryStorageInitializer;
import com.paul.initializer.StorageInitializationContext;
import com.paul.storage.Storage;
import com.paul.util.CsvReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;


@Component
public class InitializeStorageBeanPostProcessor implements BeanPostProcessor {

    @Value("${csv.delimiter}")
    private char delimiter;

    @Value("${csv.header}")
    private boolean hasHeader;

    @Value("${csv.ignoreQuotations}")
    private boolean ignoreQuotations;

    @Value("${storage.initialize}")
    private boolean storageInitialized;

    @Value("${storage.data-locations}")
    private String[] dataLocations;

    private final String MODELS_PATH = "com.paul.model";

    private final ResourceLoader resourceLoader;

    @Autowired
    public InitializeStorageBeanPostProcessor(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {

        if (bean instanceof Storage storage) {

            if (!storageInitialized) {
                return bean;
            }

            for (String filePath : dataLocations) {

                String extractedClassName = extractClassNameFromFilePath(filePath);
                Class<?> extractedClass = existsInModelsPackage(extractedClassName);
                if (extractedClass == null) {
                    return bean;
                }

                String[] headerCsv;
                List<String[]> bodyCsv;
                try (InputStream inputStream = resourceLoader.getResource(filePath).getInputStream();
                     Reader reader = new InputStreamReader(inputStream)) {
                    headerCsv = CsvReader.readCsvHeader(reader);
                    bodyCsv = CsvReader.readCsvBody(reader, delimiter, hasHeader, ignoreQuotations);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                StorageInitializationContext storageInitializationContext = new StorageInitializationContext();
                InMemoryStorageInitializer<?> strategy = storageInitializationContext.getStrategy(extractedClass);
                strategy.initialize(storage, headerCsv, bodyCsv);
            }
        }
        return bean;
    }

    private String extractClassNameFromFilePath(String filePath) {
        String fileName = filePath.substring(filePath.lastIndexOf('/') + 1);
        String className = fileName.contains(".") ? fileName.substring(0, fileName.lastIndexOf('.')) : fileName;
        return className.substring(0, 1).toUpperCase() + className.substring(1);
    }

    private Class<?> existsInModelsPackage(String className) {
        String fullClassName = MODELS_PATH + "." + className;
        try {
            return Class.forName(fullClassName);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

}
