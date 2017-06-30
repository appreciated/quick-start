package com.github.appreciated.quickstart.base.io.upload;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by appreciated on 30.06.2017.
 */
public class StringUpload extends Upload {
    private StringUploadListener stringUploadListener;

    public StringUpload(StringUploadListener stringUploadListener) {
        this.stringUploadListener = stringUploadListener;
    }

    @Override
    public void createUploadButton(com.vaadin.ui.Upload button) {
        fileAsString(button, stringUploadListener);
    }

    @FunctionalInterface
    public interface StringUploadListener {
        void onUploadFinished(String string);
    }

    public static void fileAsString(com.vaadin.ui.Upload upload, StringUploadListener listener) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        com.vaadin.ui.Upload.FinishedListener uploadListener = (com.vaadin.ui.Upload.FinishedListener) finishedEvent -> listener.onUploadFinished(new String(stream.toByteArray(), StandardCharsets.UTF_8));
        fileAsStream(upload, stream, uploadListener);
    }

}
