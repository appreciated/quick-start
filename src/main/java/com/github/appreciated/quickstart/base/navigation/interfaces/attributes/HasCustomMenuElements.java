package com.github.appreciated.quickstart.base.navigation.interfaces.attributes;

import com.vaadin.ui.Component;

import java.util.stream.Stream;

/**
 * Created by appreciated on 09.03.2017.
 */
public interface HasCustomMenuElements {
    Stream<Component> getComponents();
}
