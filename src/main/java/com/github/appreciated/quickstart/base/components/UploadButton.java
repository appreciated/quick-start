package com.github.appreciated.quickstart.base.components;

import com.github.appreciated.quickstart.base.navigation.Action;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Upload;

/**
 * Created by appreciated on 26.12.2016.
 */
public class UploadButton extends AbsoluteLayout  {

    public UploadButton(Action action){
        addStyleName("small-context-button");
        setWidth("50px");
        setHeight("50px");
        Button button = new Button();
        button.setSizeFull();
        button.setIcon(action.getResource());
        Upload upload = new Upload();
        upload.setSizeFull();
        upload.addStyleName("transparent");
        upload.setImmediate(true);
        upload.setButtonCaption(action.getName());
        action.getUpload().createUploadButton(upload);
        addComponents(button, upload);
    }

}
