package com.github.appreciated.quickstart.base.navigation;

import com.github.appreciated.quickstart.base.navigation.container.NavigationContainerView;
import com.github.appreciated.quickstart.base.navigation.container.Pager;
import com.github.appreciated.quickstart.base.navigation.interfaces.*;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by appreciated on 10.12.2016.
 * <p>
 * <p>
 * The WebsiteNavigator stores the instances of all the elements the were already called once and allows the programmer to only use one method
 */
public class WebsiteNavigator extends Navigator {

    private final NavigationDesignInterface navigatorView;
    private Subpage currentView = null;
    private AbstractOrderedLayout holder = null;

    private Map<Class<? extends Subpage>, Subpage> navigationElements = new HashMap<>();
    private Component currentComponent;
    private OnNavigateListener listener;

    /**
     * @param navigatorView The Component in which the User can navigate
     */
    public WebsiteNavigator(NavigationDesignInterface navigatorView) {
        this.holder = navigatorView.getHolder();
        this.navigatorView = navigatorView;
    }

    public void navigateToDefaultPage() {
        navigateTo(WebApplicationUI.getWebsiteDescription().getDefaultPage());
    }

    public void addNavigation(Subpage navigation) {
        navigationElements.put(navigation.getClass(), navigation);
    }

    public void navigateTo(Subpage subpageComponent) {
        if (currentView != subpageComponent) {
            currentView = subpageComponent;
            navigatorView.setPageTitleVisibility(subpageComponent.showTitle());
            navigatorView.setCurrentSearchNavigable(subpageComponent instanceof HasSearch ? (HasSearch) subpageComponent : null);
            navigatorView.setCurrentActions(subpageComponent instanceof HasContextActions ? (HasContextActions) subpageComponent : null);
            navigatorView.setCurrentContainerLabel(subpageComponent.getNavigationName());
            if (subpageComponent instanceof ContainerSubpage) {
                navigateTo((ContainerSubpage) subpageComponent);
            } else {
                setComponent(subpageComponent);
            }
        }
    }

    public void navigateTo(ContainerSubpage component) {
        NavigationContainerView container = new NavigationContainerView();
        container.getContentHolder().addComponent(component);
        setComponent(container);
    }

    public void setComponent(Component component) {
        navigatorView.allowPercentagePageHeight(component instanceof HasPercentageHeight);
        holder.removeAllComponents();
        onNavigate();
        currentComponent = component;
        holder.addComponent(currentComponent);
        if (!WebApplicationUI.isMobile()) {
            holder.setSizeFull();
        } else {
            holder.setWidth(100, Sizeable.Unit.PERCENTAGE);
        }
        holder.setComponentAlignment(currentComponent, Alignment.MIDDLE_CENTER);
    }

    public void navigateTo(Class<? extends Subpage> classKey) {
        Subpage page = navigationElements.get(classKey);
        navigateTo(page);
    }

    public NavigationDesignInterface getNavigationDesign() {
        return navigatorView;
    }

    public static void next() {
        ((WebApplicationUI) UI.getCurrent()).getNavigation().nextPagerView();
    }

    public static void last() {
        ((WebApplicationUI) UI.getCurrent()).getNavigation().lastPagerView();
    }

    public void nextPagerView() {
        if (currentComponent instanceof Pager) {
            ((Pager) currentComponent).next();
        }
    }

    public void lastPagerView() {
        if (currentComponent instanceof Pager) {
            ((Pager) currentComponent).last();
        }
    }

    public void setNavigationListener(OnNavigateListener listener) {
        this.listener = listener;
    }

    private void onNavigate() {
        if (listener != null) {
            this.listener.onNavigate();
        }
    }

    public void initNavigationElements(Stream<Subpage> subpages) {
        subpages.forEach(subpage -> addNavigation(subpage));
    }
}
