package com.github.appreciated.quickstart.base.authentication.registration;

import com.vaadin.data.PropertyDefinition;
import com.vaadin.data.PropertySet;
import com.vaadin.data.ValueProvider;
import com.vaadin.server.Setter;

import java.util.Optional;

/**
 * Created by appreciated on 05.03.2017.
 */
class FieldProperty<T> implements PropertyDefinition<T, Object> {

    private final java.lang.reflect.Field field;

    FieldProperty(java.lang.reflect.Field field) {
        this.field = field;
    }

    @Override
    public ValueProvider<T, Object> getGetter() {
        return (ValueProvider<T, Object>) user -> {
            try {
                return field.get(user);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        };
    }

    @Override
    public Optional<Setter<T, Object>> getSetter() {
        return Optional.of((Setter<T, Object>) (user, o) -> {
            try {
                field.set(user, o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public Class<Object> getType() {
        return (Class<Object>) field.getAnnotation(Field.class).type();
    }

    @Override
    public Class<T> getPropertyHolderType() {
        return (Class<T>) field.getType();
    }

    @Override
    public String getName() {
        return field.getName();
    }

    @Override
    public String getCaption() {
        return field.getAnnotation(Field.class).caption();
    }

    @Override
    public PropertySet<T> getPropertySet() {
        return null;
    }
}
