package com.github.appreciated.quickstart.base.navigation.theme;

import com.github.appreciated.quickstart.base.listeners.NavigationListener;
import com.github.appreciated.quickstart.base.pages.ComponentPage;
import com.github.appreciated.quickstart.base.pages.FinishablePage;
import com.github.appreciated.quickstart.base.pages.attributes.HasContextActions;
import com.github.appreciated.quickstart.base.pages.attributes.PageManager;

/**
 * Created by appreciated on 28.06.2017.
 */
public interface ProgressStepperView extends ComponentPage, PageManager, HasContextActions, NavigationListener, FinishablePage.FinishListener {
}
