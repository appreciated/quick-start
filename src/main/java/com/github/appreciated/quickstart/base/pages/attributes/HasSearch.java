package com.github.appreciated.quickstart.base.pages.attributes;

import com.vaadin.data.HasValue;

/**
 * Created by appreciated on 01.03.2017.
 */
public interface HasSearch extends HasValue.ValueChangeListener<String> {
    @Override
    void valueChange(HasValue.ValueChangeEvent<String> valueChangeEvent);

    default String getPlaceholder() {
        return null;
    }

    default boolean hasSearch() {
        return true;
    }
}
