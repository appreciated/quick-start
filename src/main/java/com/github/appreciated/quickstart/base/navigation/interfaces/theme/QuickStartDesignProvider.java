package com.github.appreciated.quickstart.base.navigation.interfaces.theme;

import com.github.appreciated.quickstart.base.dialog.Dialog;
import com.github.appreciated.quickstart.base.navigation.interfaces.base.ComponentSubpage;
import com.github.appreciated.quickstart.base.navigation.interfaces.base.ContainerSubpage;
import com.github.appreciated.quickstart.base.navigation.interfaces.base.Subpage;
import com.github.appreciated.quickstart.base.navigation.interfaces.components.Pager;
import com.github.appreciated.quickstart.base.navigation.interfaces.components.ProgressStepper;
import com.github.appreciated.quickstart.base.navigation.interfaces.components.SubpageNavigator;
import com.vaadin.ui.Component;
import com.vaadin.ui.Layout;
import org.vaadin.leif.splashscreen.SplashScreen;

import java.security.InvalidParameterException;

/**
 * Created by Johannes on 27.06.2017.
 */
public interface QuickStartDesignProvider {
    Class<? extends QuickStartNavigationView> getMobileDesign();

    Class<? extends QuickStartNavigationView> getDesktopDesign();

    Layout getNavigationContainer(ContainerSubpage page);

    ComponentSubpage getSubPageNavigator(SubpageNavigator subpages);

    ComponentSubpage getProgressStepper(ProgressStepper subpages);

    ComponentSubpage getPager(Pager subpages);

    Dialog getDialog();

    default Component getComponent(Subpage subpage) {
        if (subpage instanceof ContainerSubpage) {
            return getNavigationContainer((ContainerSubpage) subpage);
        }
        if (subpage instanceof Pager) {
            return getPager((Pager) subpage);
        }
        if (subpage instanceof ProgressStepper) {
            return getProgressStepper((ProgressStepper) subpage);
        }
        if (subpage instanceof SubpageNavigator) {
            return getSubPageNavigator((SubpageNavigator) subpage);
        }
        if (subpage instanceof Component) {
            return (Component) subpage;
        }
        throw new InvalidParameterException("Subpage must not be a instance of " + subpage.getClass().getName());
    }

    default SplashScreen getAnnotation() {
        return this.getClass().getAnnotation(SplashScreen.class);
    }
}
