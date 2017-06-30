package com.github.appreciated.quickstart.base.pages.actions;

import com.github.appreciated.quickstart.base.io.upload.Upload;
import com.vaadin.server.Resource;

/**
 * Created by appreciated on 02.03.2017.
 */
public class UploadAction extends Action {

    private final Upload upload;

    public UploadAction(Resource resource, Upload upload) {
        this(null, resource, upload);
    }

    public UploadAction(String title, Upload upload) {
        this(title, null, upload);
    }

    public UploadAction(String title, Resource resource, Upload upload) {
        super(resource, title);
        this.upload = upload;
    }

    public Upload getUpload() {
        return upload;
    }
}
