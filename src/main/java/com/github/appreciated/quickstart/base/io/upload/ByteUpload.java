package com.github.appreciated.quickstart.base.io.upload;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by Johannes on 30.06.2017.
 */
public class ByteUpload extends Upload {
    private File file;
    private ByteUploadListener byteUploadListener;

    public ByteUpload(ByteUploadListener byteUploadListener) {
        this.byteUploadListener = byteUploadListener;
    }

    @Override
    public void createUploadButton(com.vaadin.ui.Upload button) {
        fileAsBytes(button, byteUploadListener);
    }
    @FunctionalInterface
    public interface ByteUploadListener {
        void onUploadFinished(byte[] bytes);
    }

    public static void fileAsBytes(com.vaadin.ui.Upload upload, ByteUploadListener listener) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        com.vaadin.ui.Upload.FinishedListener uploadListener = (com.vaadin.ui.Upload.FinishedListener) finishedEvent -> listener.onUploadFinished(stream.toByteArray());
        fileAsStream(upload, stream, uploadListener);
    }
}
