package com.github.appreciated.quickstart.base.upload;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Upload {
    private File file;
    private StringUploadListener stringUploadListener;
    private ByteUploadListener byteUploadListener;
    private FileUploadListener fileUploadListener;

    public Upload(StringUploadListener stringUploadListener) {
        this.stringUploadListener = stringUploadListener;
    }

    public Upload(ByteUploadListener byteUploadListener) {
        this.byteUploadListener = byteUploadListener;
    }

    public Upload(File file, FileUploadListener fileUploadListener) {
        this.file = file;
        this.fileUploadListener = fileUploadListener;
    }

    public void createUploadButton(com.vaadin.ui.Upload button) {
        if (file != null) {
            try {
                file(file, button, fileUploadListener);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (stringUploadListener != null) {
            fileAsString(button, stringUploadListener);
        } else if (byteUploadListener != null) {
            fileAsBytes(button, byteUploadListener);
        }
    }


    public interface StringUploadListener {
        void onUploadFinished(String string);
    }

    public interface FileUploadListener {
        void onUploadFinished(File file);
    }

    public interface ByteUploadListener {
        void onUploadFinished(byte[] bytes);
    }

    public static void file(File destination, com.vaadin.ui.Upload upload, FileUploadListener listener) throws FileNotFoundException {
        FileOutputStream stream = new FileOutputStream(destination);
        com.vaadin.ui.Upload.FinishedListener uploadListener = (com.vaadin.ui.Upload.FinishedListener) finishedEvent -> listener.onUploadFinished(destination);
        fileAsStream(upload, stream, uploadListener);
    }

    public static void fileAsString(com.vaadin.ui.Upload upload, StringUploadListener listener) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        com.vaadin.ui.Upload.FinishedListener uploadListener = (com.vaadin.ui.Upload.FinishedListener) finishedEvent -> listener.onUploadFinished(new String(stream.toByteArray(), StandardCharsets.UTF_8));
        fileAsStream(upload, stream, uploadListener);
    }

    public static void fileAsBytes(com.vaadin.ui.Upload upload, ByteUploadListener listener) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        com.vaadin.ui.Upload.FinishedListener uploadListener = (com.vaadin.ui.Upload.FinishedListener) finishedEvent -> listener.onUploadFinished(stream.toByteArray());
        fileAsStream(upload, stream, uploadListener);
    }

    private static void fileAsStream(com.vaadin.ui.Upload upload, OutputStream stream, com.vaadin.ui.Upload.FinishedListener listener) {
        System.out.println("fileAsStream");
        upload.setReceiver((com.vaadin.ui.Upload.Receiver) (s, s1) -> stream);
        upload.addFinishedListener(listener);
    }

}


