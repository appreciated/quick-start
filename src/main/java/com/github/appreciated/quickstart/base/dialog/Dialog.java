package com.github.appreciated.quickstart.base.dialog;

import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;

/**
 * Created by appreciated on 22.12.2016.
 */
public abstract class Dialog {

    private VerticalLayout wrapper = new VerticalLayout();
    private Window dialog;
    private VerticalLayout dialogContentWrapper;
    private Component content;
    private HorizontalLayout buttonOrientationWrapper = new HorizontalLayout();
    private HorizontalLayout buttonWrapper = new HorizontalLayout();
    private Alignment alignment = Alignment.MIDDLE_RIGHT;
    private String title;
    private Button[] buttons;

    public void addPositiveButton(Button button, Button.ClickListener listener) {
        if (listener != null) {
            button.addClickListener(listener);
        }
        buttonOrientationWrapper.addComponent(button);
    }

    public void addNegativeButton(Button button, Button.ClickListener listener) {
        if (listener != null) {
            button.addClickListener(listener);
        }
        buttonOrientationWrapper.addComponentAsFirst(button);
    }

    public Dialog() {
    }

    public Dialog(String title, Component content) {
        this(title, content, null);
    }

    public Dialog(String title, Component content, Button... buttons) {
        initDialog(title, content, buttons);
    }

    public void initDialog(String title, Component content, Button... buttons) {
        this.title = title;
        this.content = content;
        this.buttons = buttons;
        initDialog();
    }

    public void initDialog() {
        dialog = new Window();
        dialog.setCaption(title);
        HorizontalLayout componentWrapper = new HorizontalLayout(content);
        componentWrapper.setId("window-component-wrapper");
        wrapper.setMargin(false);
        wrapper.setSpacing(false);
        dialogContentWrapper = new VerticalLayout(componentWrapper);
        Panel dialogContentPanel = new Panel(dialogContentWrapper);
        dialogContentPanel.addStyleName("borderless");
        wrapper.addComponent(dialogContentPanel);
        if (buttonOrientationWrapper.getComponentCount() > 0 || (buttons != null && buttons.length > 0)) {
            initDialogButtons();
        }
        dialog.setContent(wrapper);
        dialog.addAttachListener(attachEvent -> Page.getCurrent().addBrowserWindowResizeListener(browserWindowResizeEvent -> dialog.center()));
    }

    public void initDialogButtons() {
        wrapper.addComponent(buttonWrapper);
        buttonWrapper.setWidth(100, Sizeable.Unit.PERCENTAGE);
        buttonWrapper.setMargin(true);
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
        if (dialog == null) {
            initDialog();
        }
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

    public Button[] getButtons() {
        return buttons;
    }

    public String getTitle() {
        return title;
    }
}
