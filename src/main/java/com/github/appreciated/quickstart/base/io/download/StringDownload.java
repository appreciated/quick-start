package com.github.appreciated.quickstart.base.io.download;

import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Button;

import java.io.ByteArrayInputStream;

/**
 * Created by Johannes on 30.06.2017.
 */
public class StringDownload extends Download {

    private StringDownloadResource stringListener;

    @FunctionalInterface
    public interface StringDownloadResource {
        String onDownload();
    }

    public StringDownload(String filename, StringDownloadResource string) {
        this.filename = filename;
        this.stringListener = string;
    }

    public void createDownloadButton(Button button) {
        createStringDownloadButton(stringListener, filename, button);
    }

    public static FileDownloader createStringDownloadButton(StringDownloadResource string, String fileName, Button button) {
        final StreamResource stream = new StreamResource(
                (StreamResource.StreamSource) () -> new ByteArrayInputStream(string.onDownload().getBytes()), fileName);
        FileDownloader downloader = new FileDownloader(stream);
        downloader.extend(button);
        return downloader;
    }

}
