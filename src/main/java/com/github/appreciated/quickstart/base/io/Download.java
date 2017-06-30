package com.github.appreciated.quickstart.base.io;

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
    private ByteDownload byteListener;
    private StringDownload stringListener;
    private FileDownload fileListener;

    public interface StringDownload {
        String onDownload();
    }

    public interface FileDownload {
        File onDownload();
    }

    public interface ByteDownload {
        byte[] onDownload();
    }

    public Download(String filename, StringDownload string) {
        this.filename = filename;
        this.stringListener = string;
    }

    public Download(String filename, ByteDownload bytes) {
        this.filename = filename;
        byteListener = bytes;
    }

    public Download(FileDownload file) {
        fileListener = file;
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

    public static FileDownloader createFileDownloadButton(FileDownload file, Button button) {
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

    public static FileDownloader createStringDownloadButton(StringDownload string, String fileName, Button button) {
        final StreamResource stream = new StreamResource(
                (StreamResource.StreamSource) () -> new ByteArrayInputStream(string.onDownload().getBytes()), fileName);
        FileDownloader downloader = new FileDownloader(stream);
        downloader.extend(button);
        return downloader;
    }

    public static FileDownloader createByteDownloadButton(ByteDownload bytes, String fileName, Button button) {
        final StreamResource stream = new StreamResource(
                (StreamResource.StreamSource) () -> new ByteArrayInputStream(bytes.onDownload()), fileName);
        FileDownloader downloader = new FileDownloader(stream);
        downloader.extend(button);
        return downloader;
    }
}
