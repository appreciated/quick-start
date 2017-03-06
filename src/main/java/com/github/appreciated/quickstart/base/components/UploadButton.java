package com.github.appreciated.quickstart.base.components;

import com.github.appreciated.quickstart.base.navigation.WebApplicationUI;
import com.github.appreciated.quickstart.base.navigation.actions.UploadAction;
import com.vaadin.ui.Upload;

/**
 * Created by appreciated on 26.12.2016.
 */
public class UploadButton extends Upload  {

    public UploadButton( UploadAction action){
        if (WebApplicationUI.isMobile()) {
            addStyleName("mobile-context-button");
        } else {
            addStyleName("tab");
        }
        setImmediateMode(true);
        setIcon(action.getResource());
        setButtonCaption(action.getName());
        action.getUpload().createUploadButton(this);
    }

}
