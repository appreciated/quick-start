package com.github.appreciated.quickstart.base.navigation.interfaces.theme;

import com.github.appreciated.quickstart.base.navigation.interfaces.Finishable;
import com.github.appreciated.quickstart.base.navigation.interfaces.NavigationListener;
import com.github.appreciated.quickstart.base.navigation.interfaces.attributes.HasContextActions;
import com.github.appreciated.quickstart.base.navigation.interfaces.base.ComponentSubpage;

/**
 * Created by Johannes on 28.06.2017.
 */
public interface ProgressStepperComponent extends ComponentSubpage, HasContextActions, NavigationListener, Finishable.FinishListener {
}
