package com.github.appreciated.quickstart.base.components;

import com.github.appreciated.quickstart.base.navigation.actions.Action;
import com.github.appreciated.quickstart.base.navigation.WebsiteUI;
import com.github.appreciated.quickstart.base.navigation.actions.UploadAction;
import com.vaadin.server.Resource;
import com.vaadin.ui.Upload;

/**
 * Created by appreciated on 26.12.2016.
 */
public class UploadButton extends Upload  {

    public UploadButton(String caption, Resource icon, UploadAction action){
        if (WebsiteUI.isMobile()) {
            addStyleName("mobile-context-button");
        } else {
            addStyleName("tab");
        }
        setImmediateMode(true);
        setIcon(icon);
        setButtonCaption(action.getName());
        action.getUpload().createUploadButton(this);
    }

}
