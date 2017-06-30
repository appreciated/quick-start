package com.github.appreciated.quickstart.base.navigation.theme;

import com.github.appreciated.quickstart.base.listeners.NavigationListener;
import com.github.appreciated.quickstart.base.pages.ComponentSubpage;
import com.github.appreciated.quickstart.base.pages.Finishable;
import com.github.appreciated.quickstart.base.pages.attributes.HasContextActions;

/**
 * Created by appreciated on 28.06.2017.
 */
public interface ProgressStepperView extends ComponentSubpage, HasContextActions, NavigationListener, Finishable.FinishListener {
}
