package com.github.appreciated.quickstart.base.components;

import com.github.appreciated.quickstart.base.listeners.LayoutLeftClickListener;
import com.github.appreciated.quickstart.base.navigation.interfaces.HasSubpages;
import com.github.appreciated.quickstart.base.navigation.interfaces.Subpage;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;

import java.util.List;

/**
 * Created by appreciated on 09.03.2017.
 */
public class ProgressStepView extends HorizontalLayout {

    private final List<Subpage> pages;
    private boolean navigateable;
    private NavigationListener navigationListener;

    public ProgressStepView(HasSubpages pager) {
        this.pages = pager.getPagingElements().getSubpages();
        for (int i = 0; i < pages.size(); i++) {
            ProgressStepDesign view = new ProgressStepDesign();
            view.stepNumber.setValue(String.valueOf(i + 1));
            view.stepName.setValue(pages.get(i).getNavigationName());
            int finalI = i;
            view.addLayoutClickListener(new LayoutLeftClickListener(clickEvent -> {
                onNavigate(pages.get(finalI));
            }));
            this.addComponent(view);
        }
    }

    public void setNavigatable(boolean navigatable) {
        this.navigateable = navigatable;
    }

    private void onNavigate(Subpage subpage) {
        if (navigationListener != null) {
            navigationListener.onNavigate(subpage);
        }
    }

    public void setNavigationListener(NavigationListener navigationListener) {
        this.navigationListener = navigationListener;
    }

    public interface NavigationListener {
        void onNavigate(Component next);
    }
}
