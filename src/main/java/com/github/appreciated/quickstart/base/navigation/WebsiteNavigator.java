package com.github.appreciated.quickstart.base.navigation;

import com.github.appreciated.quickstart.base.container.NavigablePagerView;
import com.github.appreciated.quickstart.base.container.NavigationContainerView;
import com.github.appreciated.quickstart.base.interfaces.*;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.*;

import javax.xml.ws.Holder;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by appreciated on 10.12.2016.
 * <p>
 * <p>
 * The WebsiteNavigator stores the instances of all the elements the were already called once and allows the programmer to only use one method
 */
public class WebsiteNavigator extends Navigator {

    private final NavigationDesignInterface navigatorView;
    private final boolean isMobile;
    private ContextButtonClickListener contextButtonListener;
    private MenuBar.MenuItem currentTab = null;
    private HorizontalLayout holder = null;

    Map<Class<? extends Navigable>, Map.Entry<Navigable, MenuBar.MenuItem>> navigationElements = new HashMap<>();
    private Component currentComponent;
    private OnNavigateListener listener;
    private boolean isExpandButton = false;
    private Button.ClickListener clickListener;

    /**
     * @param navigatorView The Component in which the User can navigate
     * @param holder
     */
    public WebsiteNavigator(NavigationDesignInterface navigatorView, HorizontalLayout holder) {
        this.holder = holder;
        this.navigatorView = navigatorView;
        isMobile = WebsiteUI.isMobile();
    }

    public void addNavigation(MenuBar.MenuItem item, Navigable navigation) {
        navigationElements.put(navigation.getClass(), new AbstractMap.SimpleEntry<>(navigation, item));
        item.setCommand(menuItem -> {
            navigateTo(item, navigation);
            navigatorView.setCurrentContainerLabel(navigation.getNavigationName());
        });
    }

    public void navigateTo(MenuBar.MenuItem item, Navigable navigableComponent) {
        if (currentTab != item) {
            refreshTab(item);
            if (navigableComponent instanceof PagerNavigable) {
                navigateTo((PagerNavigable) navigableComponent);
            } else if (navigableComponent instanceof ContaineredNavigable) {
                navigateTo((ContaineredNavigable) navigableComponent);
            } else {
                navigateTo(navigableComponent);
            }
        }
    }

    public void refreshTab(MenuBar.MenuItem item) {
        String style = isMobile ? "mobile-tab-active" : "tab-active";
        if (item != null) {
            item.setStyleName(style);
            if (currentTab != null) {
                currentTab.setStyleName(style);
            }
            currentTab = item;
        }
    }

    public void navigateTo(ContaineredNavigable component) {
        NavigationContainerView container = new NavigationContainerView();
        container.getContentHolder().addComponent(component);
        addComponent(container);
    }

    public void navigateTo(PagerNavigable component) {
        NavigablePagerView pager = new NavigablePagerView(component);
        addComponent(pager);
    }

    public void navigateTo(Navigable component) {
        addComponent(component);
    }

    public void addComponent(Component component) {
        holder.removeAllComponents();
        onNavigate();
        currentComponent = component;
        holder.addComponent(currentComponent);
        holder.setSizeFull();
        holder.setComponentAlignment(currentComponent, Alignment.MIDDLE_CENTER);
    }

    public void navigateTo(Class<? extends Navigable> classKey) {
        Holder<Boolean> found = new Holder<>(new Boolean(false));
        if (navigationElements.containsKey(classKey)) {
            navigateTo(navigationElements.get(classKey).getValue(), navigationElements.get(classKey).getKey());
        } else {
            try {
                Constructor<? extends Navigable> constructor = classKey.getConstructor();
                Navigable instance = constructor.newInstance();
                navigationElements.put(instance.getClass(), new AbstractMap.SimpleEntry<>(instance, null));
                navigateTo(instance);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public NavigationDesignInterface getNavigationDesign() {
        return navigatorView;
    }

    public static void next() {
        ((WebsiteUI) UI.getCurrent()).getNavigation().nextPagerView();
    }

    public static void last() {
        ((WebsiteUI) UI.getCurrent()).getNavigation().lastPagerView();
    }

    public void nextPagerView() {
        if (currentComponent instanceof NavigablePagerView) {
            ((NavigablePagerView) currentComponent).next();
        }
    }

    public void lastPagerView() {
        if (currentComponent instanceof NavigablePagerView) {
            ((NavigablePagerView) currentComponent).last();
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

    public static void toggleStyle(Component component, String style) {
        if (component.getStyleName().contains(style)) {
            component.removeStyleName(style);
        } else {
            component.addStyleName(style);
        }
    }

    public static void setStyle(Component component, String style, boolean enabled) {
        if (!enabled) {
            component.removeStyleName(style);
        } else {
            component.addStyleName(style);
        }
    }
}
