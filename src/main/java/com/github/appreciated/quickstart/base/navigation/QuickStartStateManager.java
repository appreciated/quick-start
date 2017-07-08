package com.github.appreciated.quickstart.base.navigation;

import com.github.appreciated.quickstart.base.listeners.OnNavigateListener;
import com.github.appreciated.quickstart.base.navigation.theme.ContainerPageView;
import com.github.appreciated.quickstart.base.navigation.theme.PageHolder;
import com.github.appreciated.quickstart.base.navigation.theme.ProgressStepperView;
import com.github.appreciated.quickstart.base.navigation.theme.QuickStartDesignProvider;
import com.github.appreciated.quickstart.base.pages.AutonomousPage;
import com.github.appreciated.quickstart.base.pages.FinishablePage;
import com.github.appreciated.quickstart.base.pages.Page;
import com.github.appreciated.quickstart.base.pages.attributes.HasContextActions;
import com.github.appreciated.quickstart.base.pages.attributes.HasContextActions.ContextActionListener;
import com.github.appreciated.quickstart.base.pages.attributes.HasSearch;
import com.github.appreciated.quickstart.base.ui.QuickStartUI;
import com.vaadin.ui.AbstractOrderedLayout;
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
public class QuickStartStateManager implements FinishablePage.FinishListener, ContextActionListener {

    private final PageHolder navigatorView;
    private QuickStartDesignProvider provider;
    private Page currentPage = null;
    private AbstractOrderedLayout holder = null;

    private Map<Class<? extends Page>, Page> navigationElements = new HashMap<>();
    private OnNavigateListener listener;
    private Component currentComponent;

    /**
     * @param navigatorView The Component in which the User can navigate
     */
    public QuickStartStateManager(PageHolder navigatorView, QuickStartDesignProvider provider) {
        this.holder = navigatorView.getHolder();
        this.navigatorView = navigatorView;
        this.provider = provider;
    }

    public void navigateToDefaultPage() {
        navigateTo(QuickStartUI.getWebsiteDescription().getDefaultPage());
    }

    public void addNavigation(Page navigation) {
        navigationElements.put(navigation.getClass(), provider.getPage(navigation));
    }

    private void navigateTo(Page page) {
        if (page != null) {
            if (currentPage != page) {
                if (currentPage != null) {
                    currentPage.onSubpageFinish();
                }
                currentPage = page;
            }

            Page actualPage = provider.getPage(page);
            Component component = actualPage.getComponent();
            if (!(actualPage instanceof AutonomousPage)) {
                if (page instanceof ContainerPageView) {
                    Page containedPage = ((ContainerPageView) page).getContainedPage();
                    setPageTitleVisibility(containedPage.showTitle());
                    navigatorView.setCurrentContainerLabel(containedPage.getNavigationName());
                    navigatorView.setCurrentSearchNavigable(containedPage instanceof HasSearch ? (HasSearch) containedPage : null);
                    setContextActions(containedPage instanceof HasContextActions ? (HasContextActions) containedPage : null);
                    if (currentComponent != component) {
                        setComponent(actualPage.getComponent());
                        currentComponent = component;
                    }
                } else {
                    setPageTitleVisibility(actualPage.showTitle());
                    navigatorView.setCurrentSearchNavigable(actualPage instanceof HasSearch && ((HasSearch) actualPage).hasSearch() ? (HasSearch) actualPage : null);
                    navigatorView.setCurrentContainerLabel(actualPage.getNavigationName());
                    setContextActions(actualPage instanceof HasContextActions ? (HasContextActions) actualPage : null);
                    if (currentComponent != component) {
                        setComponent(actualPage.getComponent());
                        currentComponent = component;
                    }
                }


            }
            actualPage.onSubpageLoaded();
        }
    }

    /**
     *
     */
    public void setPageTitleVisibility(boolean visibility) {
        navigatorView.setPageTitleVisibility(visibility);
    }

    /**
     * @param contextActions
     */
    public void setContextActions(HasContextActions contextActions) {
        navigatorView.setCurrentActions(contextActions);
    }

    public void navigateTo(Class<? extends Page> classKey) {
        if (!navigationElements.containsKey(classKey)) {
            try {
                addNavigation(classKey.getConstructor().newInstance());
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

    public PageHolder getPageHolder() {
        return navigatorView;
    }

    public static void next() {
        ((QuickStartUI) UI.getCurrent()).getStateManager().nextPagerView();
    }

    public static void last() {
        ((QuickStartUI) UI.getCurrent()).getStateManager().lastPagerView();
    }

    public void nextPagerView() {
        /**if (currentComponent instanceof PagerView) {
         ((PagerView) currentComponent).next();
         }**/
    }

    public void lastPagerView() {
        /*if (currentComponent instanceof PagerView) {
            ((PagerView) currentComponent).last();
        }*/
    }

    @Override
    public void onFinish() {
        if (currentPage instanceof ProgressStepperView) {
            ((ProgressStepperView) currentPage).onFinish();
        }
    }

    @Override
    public void onUpdate() {
        navigateTo(currentPage);
    }

    public void setNavigationListener(OnNavigateListener listener) {
        this.listener = listener;
    }

    private void onNavigate() {
        if (listener != null) {
            this.listener.onNavigate();
        }
    }

    public Page getCurrentPage() {
        return currentPage;
    }


    public static Page getCurrentSubpage() {
        return QuickStartUI.getStateManager().getCurrentPage();
    }

    public void initNavigationElements(Stream<Page> subpages) {
        subpages.forEach(subpage -> addNavigation(subpage));
    }


    public void setComponent(Component component) {
        navigatorView.addPage(currentPage.getComponent());
    }
}
