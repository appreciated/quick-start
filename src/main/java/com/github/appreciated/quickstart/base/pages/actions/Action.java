package com.github.appreciated.quickstart.base.pages.actions;

import com.vaadin.server.Resource;

/**
 * Created by appreciated on 25.12.2016.
 */
public abstract class Action {

    private final Resource iconResource;
    private final String title;

    public Action(Resource icon, String title) {
        this.iconResource = icon;
        this.title = title;
    }

    public Resource getIconResource() {
        return iconResource;
    }

    public String getTitle() {
        return title;
    }


}
