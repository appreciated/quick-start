package com.github.appreciated.quickstart.base.io.download;

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
 * Created by appreciated on 30.06.2017.
 */
public class FileDownload extends Download {
    private ByteDownload byteListener;
    private StringDownload stringListener;
    private FileDownloadResource fileListener;

    @FunctionalInterface
    public interface FileDownloadResource {
        File onDownload();
    }


    public FileDownload(FileDownloadResource file) {
        fileListener = file;
    }

    public void createDownloadButton(Button button) {
            createFileDownloadButton(fileListener, button);
    }

    public static FileDownloader createFileDownloadButton(FileDownloadResource file, Button button) {
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

}
