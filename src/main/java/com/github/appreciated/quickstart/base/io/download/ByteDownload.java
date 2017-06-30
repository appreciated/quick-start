package com.github.appreciated.quickstart.base.io.download;

import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Button;

import java.io.ByteArrayInputStream;

/**
 * Created by appreciated on 30.06.2017.
 */
public class ByteDownload extends Download {
    private ByteDownloadResource byteListener;

    @Override
    public void createDownloadButton(Button downloadButton) {
        createByteDownloadButton(byteListener,filename,downloadButton);
    }

    @FunctionalInterface
    public interface ByteDownloadResource {
        byte[] onDownload();
    }

    public ByteDownload(String filename, ByteDownloadResource bytes) {
        this.filename = filename;
        byteListener = bytes;
    }

    public static FileDownloader createByteDownloadButton(ByteDownloadResource bytes, String fileName, Button button) {
        final StreamResource stream = new StreamResource(
                (StreamResource.StreamSource) () -> new ByteArrayInputStream(bytes.onDownload()), fileName);
        FileDownloader downloader = new FileDownloader(stream);
        downloader.extend(button);
        return downloader;
    }

}
