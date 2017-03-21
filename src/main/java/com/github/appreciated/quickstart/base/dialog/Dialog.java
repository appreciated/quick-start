package com.github.appreciated.quickstart.base.dialog;

import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;

/**
 * Created by appreciated on 22.12.2016.
 */
public abstract class Dialog {

    private final VerticalLayout wrapper;
    private final Window dialog;
    private final VerticalLayout dialogContentWrapper;
    private final Component content;
    private HorizontalLayout buttonOrientationWrapper;
    private HorizontalLayout buttonWrapper = null;
    private Alignment alignment = Alignment.MIDDLE_RIGHT;

    public interface DialogListener {
        void onClick(Window dialog);
    }

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
        dialogContentWrapper = new VerticalLayout(componentWrapper);
        Panel dialogContentPanel = new Panel(dialogContentWrapper);
        dialogContentPanel.addStyleName("borderless");
        wrapper.addComponent(dialogContentPanel);
        if (buttons != null && buttons.length > 0) {
            initDialogButtons(buttons);
        }
        dialog.setContent(wrapper);
        dialog.addAttachListener(attachEvent -> {
            Page.getCurrent().addBrowserWindowResizeListener(browserWindowResizeEvent -> dialog.center());
        });
    }

    private void initDialogButtons(Button... buttons) {
        buttonWrapper = new HorizontalLayout();
        wrapper.addComponent(buttonWrapper);
        buttonWrapper.setWidth(100, Sizeable.Unit.PERCENTAGE);

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

    public Dialog withPositiveButton(Button button, DialogListener listener) {
        if (listener != null) {
            button.addClickListener(clickEvent -> listener.onClick(dialog));
        }
        if (buttonOrientationWrapper == null) {
            initDialogButtons(null);
        }
        buttonOrientationWrapper.addComponent(button);
        return this;
    }

    public Dialog withPositiveButton(String buttonCaption, DialogListener listener) {
        return withPositiveButton(new Button(buttonCaption), listener);
    }

    public Dialog withNegativeButton(String buttonCaption) {
        return withNegativeButton(new Button(buttonCaption), clickEvent -> dialog.close());
    }


    public Dialog withNegativeButton(String buttonCaption, Button.ClickListener listener) {
        return withNegativeButton(new Button(buttonCaption), listener);
    }

    public Dialog withNegativeButton(Button button, Button.ClickListener listener) {
        if (listener != null) {
            button.addClickListener(listener);
        }
        if (buttonOrientationWrapper == null) {
            initDialogButtons(null);
        }
        buttonOrientationWrapper.addComponentAsFirst(button);
        return this;
    }

    public Dialog withButtonOrientation(Alignment alignment) {
        this.alignment = alignment;
        if (buttonWrapper != null) {
            buttonWrapper.setComponentAlignment(buttonOrientationWrapper, alignment);
        }
        return this;
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
}
