package com.github.appreciated.quickstart.base.dialog;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;

/**
 * Created by appreciated on 22.12.2016.
 */
public abstract class Dialog {

    private final Panel dialogContentPanel;
    private final VerticalLayout wrapper;

    public interface DialogListener {
        void onClick(Window dialog);
    }

    private final Window dialog;
    private final Button[] buttons;
    private final VerticalLayout dialogContentWrapper;
    private HorizontalLayout buttonOrientationWrapper;
    private HorizontalLayout componentWrapper;
    private HorizontalLayout buttonWrapper = null;
    private Alignment alignment = Alignment.MIDDLE_RIGHT;

    public Dialog(String title, Component component) {
        this(title, component, null);
    }

    public Dialog(String title, Component component, Button... buttons) {
        dialog = new Window(title);
        this.buttons = buttons;
        componentWrapper = new HorizontalLayout(component);
        componentWrapper.setId("window-component-wrapper");
        wrapper = new VerticalLayout();
        dialogContentWrapper = new VerticalLayout(componentWrapper);
        dialogContentPanel = new Panel(dialogContentWrapper);
        wrapper.addComponent(dialogContentPanel);
        if (buttons != null && buttons.length > 0) {
            initDialogButtons(buttons);
        }
        dialog.setContent(wrapper);
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

    public VerticalLayout getDialogContentWrapper() {
        return dialogContentWrapper;
    }
}
