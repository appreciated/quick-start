package com.github.appreciated.quickstart.base.navigation.theme;

import com.github.appreciated.quickstart.base.dialog.Dialog;
import com.github.appreciated.quickstart.base.pages.*;
import com.vaadin.ui.Component;
import com.vaadin.ui.Layout;
import org.vaadin.leif.splashscreen.SplashScreen;

import java.security.InvalidParameterException;

/**
 * Created by appreciated on 27.06.2017.
 */
public interface QuickStartDesignProvider {
    Class<? extends NavigationView> getMobileDesign();

    Class<? extends NavigationView> getDesktopDesign();

    LoginView getLoginView();

    Layout getNavigationContainer(ContainerSubpage page);

    ComponentSubpage getSubpageNavigator(SubpageNavigator subpages);

    ComponentSubpage getProgressStepper(ProgressStepper subpages);

    ComponentSubpage getPager(Pager subpages);

    Dialog getDialog();

    /**
     * getComponent returns depending on the paremter different Values.
     *
     * If the Parameter is not a Component Subpage, it will return either a //TODO
     *
     * @param subpage
     * @return
     */
    default Component getComponent(Subpage subpage) {
        if (subpage instanceof ContainerSubpage) {
            return getNavigationContainer((ContainerSubpage) subpage);
        } else if (subpage instanceof Pager) {
            return getPager((Pager) subpage);
        } else if (subpage instanceof ProgressStepper) {
            return getProgressStepper((ProgressStepper) subpage);
        } else if (subpage instanceof SubpageNavigator) {
            return getSubpageNavigator((SubpageNavigator) subpage);
        } else if (subpage instanceof Component) {
            return (Component) subpage;
        }
        throw new InvalidParameterException("Subpage must not be a instance of " + subpage.getClass().getName());
    }

    default SplashScreen getAnnotation() {
        return this.getClass().getAnnotation(SplashScreen.class);
    }
}
