package com.github.appreciated.quickstart.base.dialog;

import com.github.appreciated.quickstart.base.ui.QuickStartUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

/**
 * Created by Johannes on 28.06.2017.
 */
public class DialogBuilder {

    Dialog dialog = QuickStartUI.getProvider().getDialog();

    public DialogBuilder withPositiveButton(Button button, Button.ClickListener listener) {
        dialog.setPositiveButton(button, listener);
        return this;
    }

    public DialogBuilder withPositiveButton(String buttonCaption, Button.ClickListener listener) {
        return withPositiveButton(new Button(buttonCaption), listener);
    }

    public DialogBuilder withNegativeButton(String buttonCaption) {
        return withNegativeButton(new Button(buttonCaption), clickEvent -> dialog.close());
    }

    public DialogBuilder withNegativeButton(String buttonCaption, Button.ClickListener listener) {
        return withNegativeButton(new Button(buttonCaption), listener);
    }

    public DialogBuilder withNegativeButton(Button button, Button.ClickListener listener) {
        dialog.setNegativeButton(button, listener);
        return this;
    }

    public DialogBuilder withButtonOrientation(Alignment alignment) {
        dialog.setButtonOrientation(alignment);
        return this;
    }

    public DialogBuilder withTitle(String test) {
        dialog.setTitle(test);
        return this;
    }

    public DialogBuilder withContent(Component content) {
        dialog.setContent(content);
        return this;
    }

    public void show() {
       dialog.show();
    }
}
