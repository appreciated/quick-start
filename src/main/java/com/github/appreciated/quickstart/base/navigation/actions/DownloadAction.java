package com.github.appreciated.quickstart.base.navigation.actions;

import com.github.appreciated.quickstart.base.navigation.download.Download;
import com.vaadin.server.Resource;

/**
 * Created by appreciated on 02.03.2017.
 */
public class DownloadAction extends Action {

    private final Download download;

    public DownloadAction(Resource resource, Download download) {
        this(null,resource, download);
    }

    public DownloadAction(String name, Download download) {
        this(name, null, download);
    }

    public DownloadAction(String name, Resource resource, Download download) {
        super(resource, name);
        this.download = download;
    }

    public Download getDownload() {
        return download;
    }
}
