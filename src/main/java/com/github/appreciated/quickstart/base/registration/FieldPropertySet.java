package com.github.appreciated.quickstart.base.registration;

import com.vaadin.data.PropertyDefinition;
import com.vaadin.data.PropertySet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by appreciated on 05.03.2017.
 */
public class FieldPropertySet<T> implements PropertySet<T> {

    private final Class<T> className;

    public FieldPropertySet(Class<T> className) {
        this.className = className;
    }

    @Override
    public Stream<PropertyDefinition<T, ?>> getProperties() {
        List<PropertyDefinition<T, ?>> fields = new ArrayList<>();
        Arrays.stream(className.getDeclaredFields()).forEach(field -> fields.add(new FieldProperty(field)));
        return fields.stream();
    }

    @Override
    public Optional<PropertyDefinition<T, ?>> getProperty(String s) {
        return Optional.of(new FieldProperty(Arrays.stream(className.getDeclaredFields()).filter(field -> field.getName().equals(s)).findFirst().get()));
    }
}
