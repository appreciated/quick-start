package com.github.appreciated.quickstart.base.io.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Johannes on 30.06.2017.
 */
public class FileUpload extends Upload {
    private File file;

    private FileUploadListener fileUploadListener;

    public FileUpload(File file, FileUploadListener fileUploadListener) {
        this.file = file;
        this.fileUploadListener = fileUploadListener;
    }

    @Override
    public void createUploadButton(com.vaadin.ui.Upload button) {
        try {
            file(file, button, fileUploadListener);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FunctionalInterface
    public interface FileUploadListener {
        void onUploadFinished(File file);
    }

    public static void file(File destination, com.vaadin.ui.Upload upload, FileUploadListener listener) throws FileNotFoundException {
        FileOutputStream stream = new FileOutputStream(destination);
        com.vaadin.ui.Upload.FinishedListener uploadListener = (com.vaadin.ui.Upload.FinishedListener) finishedEvent -> listener.onUploadFinished(destination);
        fileAsStream(upload, stream, uploadListener);
    }

}
