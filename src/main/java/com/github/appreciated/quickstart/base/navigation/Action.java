package com.github.appreciated.quickstart.base.navigation;

import com.github.appreciated.quickstart.base.download.Download;
import com.github.appreciated.quickstart.base.upload.Upload;
import com.vaadin.server.Resource;

/**
 * Created by appreciated on 25.12.2016.
 */
public class Action {
    private Upload upload;
    private Download download;
    public enum ActionType {BUTTON, UPLOAD, DOWNLOAD}
    private final Resource resource;
    private final String name;

    private ActionType actionType;

    public Action(Resource resource, Download download) {
        this(resource, null, ActionType.DOWNLOAD);
        this.download = download;
    }

    public Action(String name, Download download) {
        this(null, name, ActionType.DOWNLOAD);
        this.download = download;
    }

    public Action(Resource resource, Upload upload) {
        this(resource, null, ActionType.UPLOAD);
        this.upload = upload;
    }

    public Action(String name, Upload upload) {
        this(null, name, ActionType.UPLOAD);
        this.upload = upload;
    }

    public Action(Resource resource, ActionType actionType) {
        this(resource, null, actionType);
    }

    public Action(String name, ActionType actionType) {
        this(null, name, actionType);
    }

    public Action(Resource resource, String name, ActionType actionType) {
        this.resource = resource;
        this.name = name;
        this.actionType = actionType;
    }

    public Resource getResource() {
        return resource;
    }

    public String getName() {
        return name;
    }

    public Action asDownload(Download download) {
        this.download = download;
        return this;
    }

    public Action asUpload(Upload upload) {
        this.upload = upload;
        return this;
    }

    public Download getDownload() {
        return download;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public Upload getUpload() {
        return upload;
    }
}
