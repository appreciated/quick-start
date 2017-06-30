package com.github.appreciated.quickstart.base.navigation.interfaces;

import com.github.appreciated.quickstart.base.navigation.interfaces.base.Subpage;
import com.github.appreciated.quickstart.base.ui.QuickStartUI;

/**
 * Created by appreciated on 19.03.2017.
 */
public interface Finishable extends Subpage {

    default void finish() {
        QuickStartUI.get().getNavigator().onFinish();
    }

    interface FinishListener {
        void onFinish();
    }
}



