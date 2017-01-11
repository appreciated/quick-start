package com.github.appreciated.quickstart.base.listeners;

import com.vaadin.event.ShortcutListener;

/**
 * Created by appreciated on 15.12.2016.
 */
public class ShortcutKeyListener extends ShortcutListener {

    private Listener listener;

    public ShortcutKeyListener(int keyCode, Listener listener) {
        super(null, keyCode, null);
        this.listener = listener;
    }

    @Override
    public void handleAction(Object o, Object o1) {
        listener.handleAction(o, o1);
    }
}
