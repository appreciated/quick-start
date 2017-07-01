package com.github.appreciated.quickstart.base.navigation.theme;

import com.github.appreciated.quickstart.base.dialog.Dialog;
import com.github.appreciated.quickstart.base.pages.PageNavigator;
import com.github.appreciated.quickstart.base.pages.attributes.ManagedPage;
import com.github.appreciated.quickstart.base.pages.attributes.PageManager;
import com.github.appreciated.quickstart.base.pages.managed.ContainedPage;
import com.github.appreciated.quickstart.base.pages.managed.HorizontalScrollPage;
import com.github.appreciated.quickstart.base.pages.managed.ProgressStepper;
import org.vaadin.leif.splashscreen.SplashScreen;

import java.security.InvalidParameterException;

/**
 * Created by appreciated on 27.06.2017.
 */
public interface QuickStartDesignProvider {
    Class<? extends PageHolder> getMobileDesign();

    Class<? extends PageHolder> getDesktopDesign();

    LoginView getLoginView();

    PageManager getNavigationContainer(ContainedPage page);

    PageManager getSubpageNavigator(PageNavigator subpages);

    PageManager getProgressStepper(ProgressStepper subpages);

    PageManager getPager(HorizontalScrollPage subpages);

    Dialog getDialog();

    /**
     * addComponent returns depending on the paremter different Values.
     *
     *
     * @param page
     * @return
     */
    default PageManager getPageManager(ManagedPage page) {
        if (page instanceof ContainedPage) {
            return getNavigationContainer((ContainedPage) page);
        } else if (page instanceof HorizontalScrollPage) {
            return getPager((HorizontalScrollPage) page);
        } else if (page instanceof ProgressStepper) {
            return getProgressStepper((ProgressStepper) page);
        } else if (page instanceof PageNavigator) {
            return getSubpageNavigator((PageNavigator) page);
        }
        throw new InvalidParameterException("Page must not be a instance of " + page.getClass().getName());
    }

    default SplashScreen getAnnotation() {
        return this.getClass().getAnnotation(SplashScreen.class);
    }
}
