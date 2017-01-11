package com.github.appreciated.quickstart.base.listeners;

import com.vaadin.event.LayoutEvents;
import com.vaadin.event.MouseEvents;
import com.vaadin.shared.MouseEventDetails;

/**
 * Created by appreciated on 10.12.2016.
 */
public class LayoutLeftClickListener implements LayoutEvents.LayoutClickListener {

    private MouseEvents.ClickListener listener;

    public LayoutLeftClickListener(MouseEvents.ClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void layoutClick(LayoutEvents.LayoutClickEvent event) {
        if (event.getButton().equals(MouseEventDetails.MouseButton.LEFT)) {
            listener.click(event);
        }
    }
}
