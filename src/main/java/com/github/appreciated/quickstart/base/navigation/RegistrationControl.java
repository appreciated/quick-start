package com.github.appreciated.quickstart.base.navigation;


import com.github.appreciated.quickstart.base.registration.Field;
import com.github.appreciated.quickstart.base.registration.FieldPropertySet;
import com.vaadin.data.Binder;
import com.vaadin.data.HasValue;
import com.vaadin.ui.TextField;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Johannes on 05.03.2017.
 */
public interface RegistrationControl<T> {
    void onUserDataEntered(T user);

    default Stream<java.lang.reflect.Field> getAttributes() {
        return Arrays.stream(getUser().getClass().getDeclaredFields()).filter(field -> field.isAnnotationPresent(Field.class));
    }

    T getUser();

    public default List<Pair<HasValue, java.lang.reflect.Field>> getFields() {
        Stream<java.lang.reflect.Field> fields = getAttributes();
        return fields.map(field -> {
            TextField textField = new TextField();
            textField.setData(field);
            textField.setCaption(field.getAnnotation(Field.class).caption());
            return new Pair<HasValue, java.lang.reflect.Field>(textField, field);
        }).collect(Collectors.toList());
    }

    public default Binder<T> getBinderForFields(List<Pair<HasValue, java.lang.reflect.Field>> components) {
        Binder<T> binder = new Binder<T>().withPropertySet(new FieldPropertySet<T>((Class<T>) getUser().getClass()));
        binder.setBean(getUser());
        components.forEach(pairConsumer -> binder.bind(pairConsumer.getKey(),pairConsumer.getValue().getName()));
        return binder;
    }
}
