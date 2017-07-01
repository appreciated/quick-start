package com.github.appreciated.quickstart.base.pages.attributes;

import com.github.appreciated.quickstart.base.pages.Page;
import com.github.appreciated.quickstart.base.pages.actions.Action;
import com.github.appreciated.quickstart.base.ui.QuickStartUI;

import java.util.List;

/**
 * Created by appreciated on 11.12.2016.
 */
public interface HasContextActions extends Page {

    List<Action> getContextActions();

    default void updateContextActions() {
            QuickStartUI.get().getStateManager().onUpdate();
    }

    interface ContextActionListener {
        void onUpdate();
    }
}