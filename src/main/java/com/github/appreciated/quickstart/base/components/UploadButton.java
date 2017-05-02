package com.github.appreciated.quickstart.base.components;

import com.github.appreciated.quickstart.base.navigation.WebApplicationUI;
import com.github.appreciated.quickstart.base.navigation.actions.UploadAction;
import com.vaadin.ui.Upload;

/**
 * Created by appreciated on 26.12.2016.
 */
public class UploadButton extends Upload {

    public UploadButton(UploadAction action) {

        if (WebApplicationUI.isMobile()) {
            //addStyleName("context-button");
            setCaption("");
            setButtonCaption("");
            setSizeFull();
            addStyleName("mobile-upload-inline-icon");
        } else {
            addStyleName("upload-inline-icon");
            addStyleName("borderless tab");
            setButtonCaption(action.getName());
        }
        addStyleName("borderless-upload-button");
        getButtonCaption();
        setImmediateMode(true);
        setIcon(action.getResource());
        action.getUpload().createUploadButton(this);
    }

}
