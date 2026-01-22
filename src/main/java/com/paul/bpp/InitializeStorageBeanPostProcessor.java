package com.paul.bpp;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.paul.storage.InitializeStorage;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class InitializeStorageBeanPostProcessor implements BeanPostProcessor {

    @Value("${csv.delimiter}")
    private String delimiter;

    @Value("${csv.header}")
    private boolean hasHeader;

    @Value("${csv.ignoreQuotations}")
    private boolean ignoreQuotations;

    private final ResourceLoader resourceLoader;

    public InitializeStorageBeanPostProcessor(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {

        if (!(bean instanceof InitializeStorage<?> storage)) {
            return bean;
        }

        String filePath = storage.getInitializeFilePath();
        if (filePath == null || filePath.isBlank()) {
            return bean;
        }

        Class<?> entityClass = storage.getEntityClass();

        try (Reader reader = openReader(filePath)) {
            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(delimiter.charAt(0))
                    .withIgnoreQuotations(ignoreQuotations)
                    .build();

            CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(parser).build();
            List<String[]> rows = csvReader.readAll();
            if (rows.isEmpty()) {
                return bean;
            }

            List<String> headers;
            int startIndex = 0;
            if (hasHeader) {
                headers = Arrays.stream(rows.get(0)).map(String::trim).collect(Collectors.toList());
                startIndex = 1;
            } else {
                // fallback: use entity declared fields order
                headers = Arrays.stream(entityClass.getDeclaredFields())
                        .map(f -> f.getName())
                        .collect(Collectors.toList());
            }

            Map<Long, Object> result = new LinkedHashMap<>();
            long idCounter = 1L;
            for (int i = startIndex; i < rows.size(); i++) {
                String[] row = rows.get(i);
                Object entity = entityClass.getDeclaredConstructor().newInstance();
                BeanWrapper bw = new BeanWrapperImpl(entity);

                for (int col = 0; col < row.length && col < headers.size(); col++) {
                    String propName = headers.get(col);
                    String raw = row[col] != null ? row[col].trim() : "";

                    if (!bw.isWritableProperty(propName)) {
                        continue;
                    }

                    Class<?> propType = bw.getPropertyType(propName);
                    Object converted = convert(raw, propType);
                    bw.setPropertyValue(propName, converted);
                }

                result.put(idCounter++, entity);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize storage from file: " + filePath, e);
        }



        return bean;
    }

    private Reader openReader(String path) throws IOException {
        // try filesystem first
        File f = new File(path);
        if (f.exists()) {
            return new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8);
        }

        // try classpath (allow both "classpath:..." and plain path)
        String cp = path.startsWith("classpath:") ? path : "classpath:" + path;
        Resource res = resourceLoader.getResource(cp);
        if (res.exists()) {
            return new InputStreamReader(res.getInputStream(), StandardCharsets.UTF_8);
        }

        // try as resource without classpath prefix
        Resource res2 = resourceLoader.getResource(path);
        if (res2.exists()) {
            return new InputStreamReader(res2.getInputStream(), StandardCharsets.UTF_8);
        }

        throw new FileNotFoundException("CSV file not found: " + path);
    }

    private Object convert(String raw, Class<?> target) {
        if (raw == null || raw.isEmpty()) {
            if (target.isPrimitive()) {
                if (target == boolean.class) return false;
                if (target == char.class) return '\0';
                if (target == byte.class) return (byte) 0;
                if (target == short.class) return (short) 0;
                if (target == int.class) return 0;
                if (target == long.class) return 0L;
                if (target == float.class) return 0f;
                if (target == double.class) return 0d;
            }
            return null;
        }

        if (target == String.class) return raw;
        if (target == Boolean.class || target == boolean.class) return Boolean.parseBoolean(raw);
        if (target == Integer.class || target == int.class) return Integer.parseInt(raw);
        if (target == Long.class || target == long.class) return Long.parseLong(raw);
        if (target == Double.class || target == double.class) return Double.parseDouble(raw);
        if (target == Float.class || target == float.class) return Float.parseFloat(raw);
        if (target == Short.class || target == short.class) return Short.parseShort(raw);
        if (target == Byte.class || target == byte.class) return Byte.parseByte(raw);
        if (target.isEnum()) {
            @SuppressWarnings("unchecked")
            Class<? extends Enum> enumType = (Class<? extends Enum>) target;
            return Enum.valueOf(enumType, raw);
        }
        if (target.isArray()) {
            // simple comma separated for arrays
            String[] parts = raw.split(",");
            Class<?> comp = target.getComponentType();
            Object arr = Array.newInstance(comp, parts.length);
            for (int i = 0; i < parts.length; i++) {
                Array.set(arr, i, convert(parts[i].trim(), comp));
            }
            return arr;
        }

        // fallback: try to set string (BeanWrapper may convert later)
        return raw;
    }


}
