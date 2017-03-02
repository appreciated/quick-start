package com.github.appreciated.quickstart.base.navigation.actions;

import com.github.appreciated.quickstart.base.download.Download;
import com.github.appreciated.quickstart.base.upload.Upload;
import com.vaadin.server.Resource;

import java.awt.event.ActionListener;

/**
 * Created by appreciated on 25.12.2016.
 */
public abstract class Action {

    private final Resource resource;
    private final String name;

    public Action(Resource resource, String name) {
        this.resource = resource;
        this.name = name;
    }

    public Resource getResource() {
        return resource;
    }

    public String getName() {
        return name;
    }

}
