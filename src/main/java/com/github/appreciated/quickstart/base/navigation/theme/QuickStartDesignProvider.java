package com.github.appreciated.quickstart.base.navigation.theme;

import com.github.appreciated.quickstart.base.dialog.Dialog;
import com.github.appreciated.quickstart.base.pages.NavigatorPage;
import com.github.appreciated.quickstart.base.pages.Page;
import com.github.appreciated.quickstart.base.pages.attributes.ManagedPage;
import com.github.appreciated.quickstart.base.pages.attributes.PageManager;
import com.github.appreciated.quickstart.base.pages.managed.ContainedPage;
import com.github.appreciated.quickstart.base.pages.managed.Pager;
import com.github.appreciated.quickstart.base.pages.managed.ProgressStepPage;
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

    PageManager getSubpageNavigator(NavigatorPage subpages);

    PageManager getProgressStepper(ProgressStepPage subpages);

    PageManager getPager(Pager subpages);

    Dialog getDialog();

    /**
     * addComponent returns depending on the paremter different Values.
     *
     * @param page
     * @return
     */
    default PageManager getPageManager(ManagedPage page) {
        if (page instanceof ContainedPage) {
            return getNavigationContainer((ContainedPage) page);
        } else if (page instanceof Pager) {
            return getPager((Pager) page);
        } else if (page instanceof ProgressStepPage) {
            return getProgressStepper((ProgressStepPage) page);
        } else if (page instanceof NavigatorPage) {
            return getSubpageNavigator((NavigatorPage) page);
        }
        throw new InvalidParameterException("Page must not be a instance of " + page.getClass().getName());
    }

    default Page getPage(Page page) {
        if (page instanceof ManagedPage) {
            return ((ManagedPage) page).getPageManager();
        } else {
            return page;
        }
    }

    default SplashScreen getAnnotation() {
        return this.getClass().getAnnotation(SplashScreen.class);
    }
}
