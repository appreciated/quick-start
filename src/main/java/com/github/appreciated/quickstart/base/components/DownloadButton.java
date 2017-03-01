package com.github.appreciated.quickstart.base.components;

import com.github.appreciated.quickstart.base.navigation.Action;
import com.github.appreciated.quickstart.base.navigation.WebsiteUI;
import com.vaadin.ui.Button;

/**
 * Created by appreciated on 26.12.2016.
 */
public class DownloadButton extends Button {

    public DownloadButton(Action action) {
        setIcon(action.getResource());
        addStyleName( WebsiteUI.isMobile() ? "mobile-context-button" : "tab");
        action.getDownload().createDownloadButton(this);
    }
}
