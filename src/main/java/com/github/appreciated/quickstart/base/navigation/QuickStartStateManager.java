package com.github.appreciated.quickstart.base.navigation;

import com.github.appreciated.quickstart.base.components.Helper;
import com.github.appreciated.quickstart.base.listeners.OnNavigateListener;
import com.github.appreciated.quickstart.base.navigation.theme.NavigationView;
import com.github.appreciated.quickstart.base.navigation.theme.PagerView;
import com.github.appreciated.quickstart.base.navigation.theme.QuickStartDesignProvider;
import com.github.appreciated.quickstart.base.pages.Finishable;
import com.github.appreciated.quickstart.base.pages.Subpage;
import com.github.appreciated.quickstart.base.pages.attributes.HasContextActions;
import com.github.appreciated.quickstart.base.pages.attributes.HasSearch;
import com.github.appreciated.quickstart.base.ui.QuickStartUI;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by appreciated on 10.12.2016.
 * <p>
 * <p>
 * The QuickStartStateManager stores the instances of all elements the were already called once and allows the programmer to only use one method
 */
public class QuickStartStateManager implements Finishable.FinishListener {

    private final NavigationView navigatorView;
    private QuickStartDesignProvider provider;
    private Subpage currentView = null;
    private AbstractOrderedLayout holder = null;

    private Map<Class<? extends Subpage>, Subpage> navigationElements = new HashMap<>();
    private Component currentComponent;
    private OnNavigateListener listener;

    /**
     * @param navigatorView The Component in which the User can navigate
     */
    public QuickStartStateManager(NavigationView navigatorView, QuickStartDesignProvider provider) {
        this.holder = navigatorView.getHolder();
        this.navigatorView = navigatorView;
        this.provider = provider;
    }

    public void navigateToDefaultPage() {
        navigateTo(QuickStartUI.getWebsiteDescription().getDefaultPage());
    }

    public void addNavigation(Subpage navigation) {
        navigationElements.put(navigation.getClass(), navigation);
    }

    public void navigateTo(Subpage subpageComponent) {
        if (currentView != subpageComponent) {
            currentView = subpageComponent;
            navigatorView.onNavigate(subpageComponent);
            navigatorView.setPageTitleVisibility(subpageComponent.showTitle());
            navigatorView.setCurrentSearchNavigable(subpageComponent instanceof HasSearch ? (HasSearch) subpageComponent : null);
            navigatorView.setCurrentActions(subpageComponent instanceof HasContextActions ? (HasContextActions) subpageComponent : null);
            if (subpageComponent instanceof HasContextActions) {
                ((HasContextActions) subpageComponent).setContextActionListener(() -> navigatorView.setCurrentActions((HasContextActions) subpageComponent));
            }
            navigatorView.setCurrentContainerLabel(subpageComponent.getNavigationName());
            setComponent(provider.getComponent(subpageComponent));
        }
    }

    public void setComponent(Component component) {
        Helper.prepareContainerForComponent(navigatorView.getContainerView(), component);
        if (component instanceof Subpage) {
            if (component instanceof HasContextActions) {
                navigatorView.setCurrentActions(component instanceof HasContextActions ? (HasContextActions) component : null);
                ((HasContextActions) component).setContextActionListener(() -> navigatorView.setCurrentActions((HasContextActions) component));
            }
            navigatorView.setPageTitleVisibility(((Subpage) component).showTitle());
        }
        navigatorView.allowPercentagePageHeight(Helper.requiresPercentageHeight(component));
        holder.removeAllComponents();
        onNavigate();
        currentComponent = component;

        holder.addComponent(currentComponent);
        navigatorView.onComponentAdded(currentComponent);
        if (!QuickStartUI.isMobile()) {
            holder.setSizeFull();
        } else {
            holder.setWidth(100, Sizeable.Unit.PERCENTAGE);
        }
        holder.setComponentAlignment(currentComponent, Alignment.MIDDLE_CENTER);
    }

    public void navigateTo(Class<? extends Subpage> classKey) {
        if (!navigationElements.containsKey(classKey)) {
            try {
                Subpage instance = classKey.getConstructor().newInstance();
                navigationElements.put(classKey, instance);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        Subpage element = navigationElements.get(classKey);
        navigateTo(element);
        navigatorView.onNavigate(element);
    }

    public NavigationView getNavigationDesign() {
        return navigatorView;
    }

    public static void next() {
        ((QuickStartUI) UI.getCurrent()).getStateManager().nextPagerView();
    }

    public static void last() {
        ((QuickStartUI) UI.getCurrent()).getStateManager().lastPagerView();
    }

    public void nextPagerView() {
        if (currentComponent instanceof PagerView) {
            ((PagerView) currentComponent).next();
        }
    }

    public void lastPagerView() {
        if (currentComponent instanceof PagerView) {
            ((PagerView) currentComponent).last();
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

    public Subpage getCurrentPage() {
        return currentView;
    }

    public Component getCurrentComponent() {
        return currentComponent;
    }

    public static Subpage getCurrentSubpage() {
        return QuickStartUI.getStateManager().getCurrentPage();
    }

    public void initNavigationElements(Stream<Subpage> subpages) {
        subpages.forEach(subpage -> addNavigation(subpage));
    }

    public void onFinish() {
        if (this.currentComponent instanceof Finishable.FinishListener) {
            ((Finishable.FinishListener) this.currentComponent).onFinish();
        }
    }
}
