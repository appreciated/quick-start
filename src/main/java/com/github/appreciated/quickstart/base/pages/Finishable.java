package com.github.appreciated.quickstart.base.pages;

import com.github.appreciated.quickstart.base.ui.QuickStartUI;

/**
 * Created by appreciated on 19.03.2017.
 */
public interface Finishable extends Subpage {

    default void finish() {
        QuickStartUI.get().getStateManager().onFinish();
    }

    interface FinishListener {
        void onFinish();
    }
}



