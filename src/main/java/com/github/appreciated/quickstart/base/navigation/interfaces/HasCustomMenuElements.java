package com.github.appreciated.quickstart.base.navigation.interfaces;

import com.vaadin.ui.Component;

import java.util.stream.Stream;

/**
 * Created by Johannes on 09.03.2017.
 */
public interface HasCustomMenuElements {
    Stream<Component> getComponents();
}
