package com.github.appreciated.quickstart.base.io.upload;

import java.io.OutputStream;

public abstract class Upload {

    public abstract void createUploadButton(com.vaadin.ui.Upload button);

    protected static void fileAsStream(com.vaadin.ui.Upload upload, OutputStream stream, com.vaadin.ui.Upload.FinishedListener listener) {
        upload.setReceiver((com.vaadin.ui.Upload.Receiver) (s, s1) -> stream);
        upload.addFinishedListener(listener);
    }
}


