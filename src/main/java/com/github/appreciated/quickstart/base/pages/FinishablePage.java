package com.github.appreciated.quickstart.base.pages;

import com.github.appreciated.quickstart.base.navigation.theme.QuickStartDesignProvider;
import com.github.appreciated.quickstart.base.ui.QuickStartUI;

/**
 * Created by appreciated on 19.03.2017.
 */
public interface FinishablePage extends Page {

    default void finish() {
        //QuickStartDesignProvider.get().getStateManager().onFinish();
    }

    interface FinishListener {
        void onFinish();
    }
}



