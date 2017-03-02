package com.github.appreciated.quickstart.base.components;

import com.github.appreciated.quickstart.base.navigation.WebsiteUI;
import com.github.appreciated.quickstart.base.navigation.actions.DownloadAction;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;

/**
 * Created by appreciated on 26.12.2016.
 */
public class DownloadButton extends Button {

    public DownloadButton(String caption, Resource icon, DownloadAction action) {
        setIcon(action.getResource());
        if (WebsiteUI.isMobile()) {
            addStyleName("mobile-context-button");
        } else {
            addStyleName("tab");
        }
        action.getDownload().createDownloadButton(this);
        setCaption(caption);
        setIcon(icon);
    }
}
