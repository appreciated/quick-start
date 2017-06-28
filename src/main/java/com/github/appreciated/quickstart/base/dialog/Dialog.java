package com.github.appreciated.quickstart.base.dialog;

import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;

/**
 * Created by appreciated on 22.12.2016.
 */
public abstract class Dialog {

    private VerticalLayout wrapper;
    private Window dialog;
    private VerticalLayout dialogContentWrapper;
    private Component content;
    private HorizontalLayout buttonOrientationWrapper;
    private HorizontalLayout buttonWrapper;
    private Alignment alignment = Alignment.MIDDLE_RIGHT;
    private String title;

    public void setPositiveButton(Button button, Button.ClickListener listener) {
        if (listener != null) {
            button.addClickListener(listener);
        }
        if (buttonOrientationWrapper == null) {
            initDialogButtons(null);
        }
        buttonOrientationWrapper.addComponent(button);
    }

    public void setNegativeButton(Button button, Button.ClickListener listener) {
        if (listener != null) {
            button.addClickListener(listener);
        }
        if (buttonOrientationWrapper == null) {
            initDialogButtons(null);
        }
        buttonOrientationWrapper.addComponentAsFirst(button);
    }

    public Dialog(){}

    public Dialog(String title, Component component) {
        this(title, component, null);
    }

    public Dialog(String title, Component component, Button... buttons) {
        dialog = new Window(title);
        Button[] buttons1 = buttons;
        content = component;
        HorizontalLayout componentWrapper = new HorizontalLayout(content);
        componentWrapper.setId("window-component-wrapper");
        wrapper = new VerticalLayout();
        wrapper.setMargin(false);
        wrapper.setSpacing(false);
        dialogContentWrapper = new VerticalLayout(componentWrapper);
        Panel dialogContentPanel = new Panel(dialogContentWrapper);
        dialogContentPanel.addStyleName("borderless");
        wrapper.addComponent(dialogContentPanel);
        if (buttons != null && buttons.length > 0) {
            initDialogButtons(buttons);
        }
        buttonWrapper = new HorizontalLayout();
        dialog.setContent(wrapper);
        dialog.addAttachListener(attachEvent -> {
            Page.getCurrent().addBrowserWindowResizeListener(browserWindowResizeEvent -> dialog.center());
        });
    }

    private void initDialogButtons(Button... buttons) {
        wrapper.addComponent(buttonWrapper);
        buttonWrapper.setWidth(100, Sizeable.Unit.PERCENTAGE);
        buttonWrapper.setMargin(true);
        buttonOrientationWrapper = new HorizontalLayout();
        buttonWrapper.addComponent(buttonOrientationWrapper);
        buttonOrientationWrapper.setSpacing(true);
        buttonWrapper.setComponentAlignment(buttonOrientationWrapper, alignment);
        if (buttons != null) {
            for (Button button : buttons) {
                buttonOrientationWrapper.addComponent(button);
            }
        }
    }

    public HorizontalLayout getButtonWrapper() {
        return buttonWrapper;
    }

    public HorizontalLayout getButtonOrientationWrapper() {
        return buttonOrientationWrapper;
    }


    public Dialog show() {
        UI.getCurrent().addWindow(dialog);
        return this;
    }

    public void close() {
        dialog.close();
    }

    public Window getWindow() {
        return dialog;
    }

    public Component getContent() {
        return content;
    }

    public VerticalLayout getDialogContentWrapper() {
        return dialogContentWrapper;
    }

    public void setButtonOrientation(Alignment buttonOrientation) {
        this.alignment = alignment;
        if (buttonWrapper != null) {
            buttonWrapper.setComponentAlignment(buttonOrientationWrapper, alignment);
        }
    }

    public void setContent(Component content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
