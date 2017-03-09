package com.github.appreciated.quickstart.base.navigation;


import com.github.appreciated.quickstart.base.authentication.registration.Field;
import com.github.appreciated.quickstart.base.authentication.registration.FieldPropertySet;
import com.github.appreciated.quickstart.base.authentication.registration.RegistrationResult;
import com.vaadin.data.Binder;
import com.vaadin.data.HasValue;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.ui.TextField;
import javafx.util.Pair;

import javax.xml.ws.Holder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by appreciated on 05.03.2017.
 */
public interface RegistrationControl<T> {
    RegistrationResult checkUserRegistrationValidity(T user);

    default Stream<java.lang.reflect.Field> getAttributes(T t) {
        return Arrays.stream(getUser().getClass().getDeclaredFields()).filter(field -> field.isAnnotationPresent(Field.class));
    }

    T getUser();

    default List<Pair<HasValue, java.lang.reflect.Field>> getFields() {
        Stream<java.lang.reflect.Field> fields = getAttributes(getUser());
        return fields.map(field -> {
            TextField textField = new TextField();
            textField.setData(field);
            Field annotation = field.getAnnotation(Field.class);
            textField.setCaption(annotation.caption());
            textField.setRequiredIndicatorVisible(annotation.required());
            return new Pair<HasValue, java.lang.reflect.Field>(textField, field);
        }).collect(Collectors.toList());
    }

    default Binder<T> getBinderForFields(List<Pair<HasValue, java.lang.reflect.Field>> components) {
        Binder<T> binder = new Binder<T>().withPropertySet(new FieldPropertySet<T>((Class<T>) getUser().getClass()));
        binder.setBean(getUser());

        components.forEach(pairConsumer -> binder.withValidator((Validator<T>) (t, valueContext) -> RegistrationControl.this.isFieldEmpty(t))
                .bind(pairConsumer.getKey(), pairConsumer.getValue().getName()));
        return binder;
    }

    default ValidationResult isFieldEmpty(T context) {
        Holder<Boolean> holder = new Holder<>(Boolean.TRUE);
        getAttributes(context).forEach((java.lang.reflect.Field field) -> {
            try {
                String s = (String) field.get(context);
                if (s == null || s.length() == 0) {
                    holder.value = false;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return holder.value ? ValidationResult.ok() : ValidationResult.error("test");
    }
}
