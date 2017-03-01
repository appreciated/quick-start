package com.github.appreciated.quickstart.base.interfaces;

import com.vaadin.data.HasValue;

/**
 * Created by Johannes on 01.03.2017.
 */
public interface SearchNavigable extends HasValue.ValueChangeListener<String> {
    @Override
    void valueChange(HasValue.ValueChangeEvent<String> valueChangeEvent);
}
