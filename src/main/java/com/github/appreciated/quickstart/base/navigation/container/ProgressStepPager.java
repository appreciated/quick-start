package com.github.appreciated.quickstart.base.navigation.container;

import com.github.appreciated.quickstart.base.components.ProgressStepView;
import com.github.appreciated.quickstart.base.navigation.actions.Action;
import com.github.appreciated.quickstart.base.navigation.actions.CustomAction;
import com.github.appreciated.quickstart.base.navigation.interfaces.ContainerSubpage;
import com.github.appreciated.quickstart.base.navigation.interfaces.HasContextActions;
import com.github.appreciated.quickstart.base.navigation.interfaces.HasSubpages;
import com.github.appreciated.quickstart.base.navigation.interfaces.Subpage;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

import java.util.Arrays;
import java.util.List;

/**
 * Created by appreciated on 09.03.2017.
 */
public abstract class ProgressStepPager extends VerticalLayout implements Subpage, HasContextActions, HasSubpages, ProgressStepView.NavigationListener {

    private final ProgressStepView progressStepView;
    private final List<Subpage> pages;
    private final Subpage currentPage;

    public ProgressStepPager() {
        progressStepView = new ProgressStepView(this);
        progressStepView.setNavigatable(isNavigatable());
        if (isNavigatable()) {
            progressStepView.setNavigationListener(this);
        }

        this.pages = getPagingElements().getSubpages();
        this.currentPage = pages.get(0);
        setNewContent(currentPage);
    }

    @Override
    public void onNavigate(Component next) {
        setNewContent(next);
    }

    @Override
    public boolean showTitle() {
        return false;
    }

    @Override
    public List<Action> getContextActions() {
        return Arrays.asList(new CustomAction(progressStepView) {
            @Override
            public boolean isMobileCompliant() {
                return false;
            }
        });
    }

    public boolean isNavigatable() {
        return false;
    }

    public void setNewContent(Component content) {
        Component actualContent = null;
        if (content instanceof ContainerSubpage) {
            NavigationContainerView container = new NavigationContainerView();
            container.getContentHolder().addComponent(content);
            actualContent = container;
        } else {
            actualContent = content;
        }
        this.removeAllComponents();
        this.addComponent(actualContent);
    }

    public void next() {
        int next = pages.indexOf(currentPage) + 1;
        if (next < pages.size()) {
            setNewContent(pages.get(next));
        }
    }

    public void last() {
        int last = pages.indexOf(currentPage) - 1;
        if (last >= 0) {
            setNewContent(pages.get(last));
        }
    }

}
