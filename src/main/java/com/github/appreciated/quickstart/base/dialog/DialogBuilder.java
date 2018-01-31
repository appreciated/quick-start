package com.github.appreciated.quickstart.base.dialog;

import com.github.appreciated.quickstart.base.navigation.theme.QuickStartDesignProvider;
import com.github.appreciated.quickstart.base.ui.QuickStartUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

/**
 * Created by appreciated on 28.06.2017.
 */
public class DialogBuilder {

    Dialog dialog = QuickStartDesignProvider.get().getDialog();

    public DialogBuilder withPositiveButton(Button button, DialogClickListener listener) {
        dialog.addPositiveButton(button, clickEvent -> listener.onClick(dialog));
        return this;
    }

    public DialogBuilder withPositiveButton(String buttonCaption, DialogClickListener listener) {
        return withPositiveButton(new Button(buttonCaption), listener);
    }

    public DialogBuilder withNegativeButton(String buttonCaption) {
        return withNegativeButton(new Button(buttonCaption), clickEvent -> dialog.close());
    }

    public DialogBuilder withNegativeButton(String buttonCaption, DialogClickListener listener) {
        return withNegativeButton(new Button(buttonCaption), listener);
    }

    public DialogBuilder withNegativeButton(Button button, DialogClickListener listener) {
        dialog.addNegativeButton(button, clickEvent -> listener.onClick(dialog));
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

    public Dialog build() {
        return dialog;
    }

    public void show() {
        dialog.show();
    }

    public interface DialogClickListener {
        void onClick(Dialog dialog);
    }

}
