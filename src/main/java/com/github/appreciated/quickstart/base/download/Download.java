package com.github.appreciated.quickstart.base.download;

import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Button;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by appreciated on 22.12.2016.
 */
public class Download {
    private String content;
    private File file;
    private byte[] bytes;
    private String filename;

    public Download(String filename, String content) {
        this.filename = filename;
        this.content = content;
    }

    public Download(String filename, byte[] bytes) {
        this.filename = filename;
        this.bytes = bytes;
    }

    public Download(File file) {
        this.file = file;
    }

    public void createDownloadButton(Button button) {
        if (file != null) {
            createFileDownloadButton(file, button);
        } else if (content != null) {
            createStringDownloadButton(content, filename, button);
        } else if (bytes != null) {
            createByteDownloadButton(bytes, filename, button);
        }
    }


    public static FileDownloader createFileDownloadButton(File file, Button button) {
        final StreamResource stream = new StreamResource(
                (StreamResource.StreamSource) () -> {
                    try {
                        return new ByteArrayInputStream(Files.readAllBytes(Paths.get(file.toURI())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }, file.getName());
        FileDownloader downloader = new FileDownloader(stream);
        downloader.extend(button);
        return downloader;
    }

    public static FileDownloader createStringDownloadButton(String fileContent, String fileName, Button button) {
        final StreamResource stream = new StreamResource(
                (StreamResource.StreamSource) () -> new ByteArrayInputStream(fileContent.getBytes()), fileName);
        FileDownloader downloader = new FileDownloader(stream);
        downloader.extend(button);
        return downloader;
    }

    public static FileDownloader createByteDownloadButton(byte[] bytes, String fileName, Button button) {
        final StreamResource stream = new StreamResource(
                (StreamResource.StreamSource) () -> new ByteArrayInputStream(bytes), fileName);
        FileDownloader downloader = new FileDownloader(stream);
        downloader.extend(button);
        return downloader;
    }
}
