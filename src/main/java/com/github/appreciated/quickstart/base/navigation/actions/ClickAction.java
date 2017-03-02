package com.github.appreciated.quickstart.base.navigation.actions;

import com.vaadin.server.Resource;

import java.awt.event.ActionListener;

/**
 * Created by Johannes on 02.03.2017.
 */
public class ClickAction extends Action {

    private final ActionListener listener;

    public ClickAction(String name, ActionListener listener) {
        this(name, null, listener);
    }

    public ClickAction(Resource resource, ActionListener listener) {
        this(null, resource, listener);
    }

    public ClickAction(String name, Resource resource, ActionListener listener) {
        super(resource, name);
        this.listener = listener;
    }

    public ActionListener getListener() {
        return listener;
    }
}
