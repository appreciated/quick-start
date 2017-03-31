package com.github.appreciated.quickstart.base.navigation.download;

import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Button;

import javax.xml.ws.Holder;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by appreciated on 22.12.2016.
 */
public class Download {
    private String filename;
    private ByteDownloadListener byteListener;
    private StringDownloadListener stringListener;
    private FileDownloadListener fileListener;

    public interface StringDownloadListener {
        String onDownload();
    }

    public interface FileDownloadListener {
        File onDownload();
    }

    public interface ByteDownloadListener {
        byte[] onDownload();
    }

    public Download(String filename, StringDownloadListener listener) {
        this.filename = filename;
        this.stringListener = listener;
    }

    public Download(String filename, ByteDownloadListener listener) {
        this.filename = filename;
        byteListener = listener;
    }

    public Download(FileDownloadListener fileDownloadListener) {
        fileListener = fileDownloadListener;
    }

    public void createDownloadButton(Button button) {
        if (fileListener != null) {
            createFileDownloadButton(fileListener, button);
        } else if (stringListener != null) {
            createStringDownloadButton(stringListener, filename, button);
        } else if (byteListener != null) {
            createByteDownloadButton(byteListener, filename, button);
        }
    }

    public static FileDownloader createFileDownloadButton(FileDownloadListener file, Button button) {
        Holder<File> fileHolder = new Holder<>();
        final StreamResource stream = new StreamResource(
                (StreamResource.StreamSource) () -> {
                    try {
                        fileHolder.value = file.onDownload();
                        return new ByteArrayInputStream(Files.readAllBytes(Paths.get(fileHolder.value.toURI())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }, fileHolder.value.getName());
        FileDownloader downloader = new FileDownloader(stream);
        downloader.extend(button);
        return downloader;
    }

    public static FileDownloader createStringDownloadButton(StringDownloadListener listener, String fileName, Button button) {
        final StreamResource stream = new StreamResource(
                (StreamResource.StreamSource) () -> new ByteArrayInputStream(listener.onDownload().getBytes()), fileName);
        FileDownloader downloader = new FileDownloader(stream);
        downloader.extend(button);
        return downloader;
    }

    public static FileDownloader createByteDownloadButton(ByteDownloadListener listener, String fileName, Button button) {
        final StreamResource stream = new StreamResource(
                (StreamResource.StreamSource) () -> new ByteArrayInputStream(listener.onDownload()), fileName);
        FileDownloader downloader = new FileDownloader(stream);
        downloader.extend(button);
        return downloader;
    }
}
