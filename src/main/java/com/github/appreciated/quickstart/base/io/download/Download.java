package com.github.appreciated.quickstart.base.io.download;

/**
 * Created by appreciated on 22.12.2016.
 */
public abstract class Download {
    protected String filename;

    public abstract void createDownloadButton(com.vaadin.ui.Button downloadButton);
}
