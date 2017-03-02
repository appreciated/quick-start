package com.github.appreciated.quickstart.base.navigation.actions;

import com.github.appreciated.quickstart.base.upload.Upload;
import com.vaadin.server.Resource;

/**
 * Created by Johannes on 02.03.2017.
 */
public class UploadAction extends Action {

    private final Upload upload;

    public UploadAction(Resource resource, Upload upload) {
        this(null, resource, upload);
    }

    public UploadAction(String name, Upload upload) {
        this(name, null, upload);
    }

    public UploadAction(String name, Resource resource, Upload upload) {
        super(resource, name);
        this.upload = upload;
    }

    public Upload getUpload() {
        return upload;
    }
}
