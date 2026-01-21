package com.paul.bpp;

import com.paul.model.Identifiable;
import com.paul.parser.CsvGenericParser;
import com.paul.storage.InitializeStorage;
import lombok.Data;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class InitializeStorageBeanPostProcessor implements BeanPostProcessor {

    private final CsvGenericParser parser;

    public InitializeStorageBeanPostProcessor(CsvGenericParser parser) {
        this.parser = parser;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {

        if (!(bean instanceof InitializeStorage<?, ?> storage)) {
            return bean;
        }

        List<? extends Identifiable<?>> data = parser.parse(
                storage.getInitializeFilePath(),
                storage.getEntityClass()
        );

        Map<Object, Object> map = data.stream()
                .collect(Collectors.toMap(Identifiable::getId, o -> o));

        try {
            Field storage1 = bean.getClass().getDeclaredField("storage");
            ReflectionUtils.makeAccessible(storage1);
            ReflectionUtils.setField(storage1, storage, map);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        return bean;
    }
}
