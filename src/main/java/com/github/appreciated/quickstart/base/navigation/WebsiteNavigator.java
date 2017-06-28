package com.github.appreciated.quickstart.base.navigation;

import com.github.appreciated.quickstart.base.navigation.interfaces.OnNavigateListener;
import com.github.appreciated.quickstart.base.navigation.interfaces.attributes.HasContextActions;
import com.github.appreciated.quickstart.base.navigation.interfaces.attributes.HasPercentageHeight;
import com.github.appreciated.quickstart.base.navigation.interfaces.attributes.HasSearch;
import com.github.appreciated.quickstart.base.navigation.interfaces.base.ContainerSubpage;
import com.github.appreciated.quickstart.base.navigation.interfaces.base.Subpage;
import com.github.appreciated.quickstart.base.navigation.interfaces.theme.QuickStartDesignProvider;
import com.github.appreciated.quickstart.base.navigation.interfaces.theme.QuickStartNavigationView;
import com.github.appreciated.quickstart.base.navigation.interfaces.theme.QuickStartPager;
import com.github.appreciated.quickstart.base.ui.QuickStartUI;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;

import java.lang.reflect.InvocationTargetException;
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

    private final QuickStartNavigationView navigatorView;
    private QuickStartDesignProvider provider;
    private Subpage currentView = null;
    private AbstractOrderedLayout holder = null;

    private Map<Class<? extends Subpage>, Subpage> navigationElements = new HashMap<>();
    private Component currentComponent;
    private OnNavigateListener listener;

    /**
     * @param navigatorView The Component in which the User can navigate
     */
    public WebsiteNavigator(QuickStartNavigationView navigatorView, QuickStartDesignProvider provider) {
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
            navigatorView.setPageTitleVisibility(subpageComponent.showTitle());
            navigatorView.setCurrentSearchNavigable(subpageComponent instanceof HasSearch ? (HasSearch) subpageComponent : null);
            navigatorView.setCurrentActions(subpageComponent instanceof HasContextActions ? (HasContextActions) subpageComponent : null);
            if (subpageComponent instanceof HasContextActions) {
                ((HasContextActions) subpageComponent).setContextActionListener(() -> navigatorView.setCurrentActions((HasContextActions) subpageComponent));
            }
            navigatorView.setCurrentContainerLabel(subpageComponent.getNavigationName());
            if (subpageComponent instanceof ContainerSubpage) {
                navigateTo((ContainerSubpage) subpageComponent);
            } else {
                setComponent(provider.getComponent(subpageComponent), false);
            }
        }
    }

    public void navigateTo(ContainerSubpage subpage) {
        Layout container = QuickStartUI.getProvider().getNavigationContainer(subpage);
        if (subpage.hasPadding()) {
            container.addStyleName("container-padding");
        }
        boolean hasPercentageHeight = subpage instanceof HasPercentageHeight;
        if (hasPercentageHeight) {
            container.setSizeFull();
        }
        container.addComponent(subpage);
        setComponent(container, hasPercentageHeight);
    }

    public void setComponent(Component component, boolean setPercentageHeight) {
        navigatorView.allowPercentagePageHeight(component instanceof HasPercentageHeight || setPercentageHeight);
        holder.removeAllComponents();
        onNavigate();
        currentComponent = component;
        holder.addComponent(currentComponent);
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
        navigateTo(navigationElements.get(classKey));
    }

    public QuickStartNavigationView getNavigationDesign() {
        return navigatorView;
    }

    public static void next() {
        ((QuickStartUI) UI.getCurrent()).getNavigation().nextPagerView();
    }

    public static void last() {
        ((QuickStartUI) UI.getCurrent()).getNavigation().lastPagerView();
    }

    public void nextPagerView() {
        if (currentComponent instanceof QuickStartPager) {
            ((QuickStartPager) currentComponent).next();
        }
    }

    public void lastPagerView() {
        if (currentComponent instanceof QuickStartPager) {
            ((QuickStartPager) currentComponent).last();
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

    private Subpage getCurrentPage() {
        return currentView;
    }

    public static Subpage getCurrentSubpage() {
        return QuickStartUI.getNavigation().getCurrentPage();
    }

    public void initNavigationElements(Stream<Subpage> subpages) {
        subpages.forEach(subpage -> addNavigation(subpage));
    }
}
