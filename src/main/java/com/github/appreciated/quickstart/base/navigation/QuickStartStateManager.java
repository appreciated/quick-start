package com.github.appreciated.quickstart.base.navigation;

import com.github.appreciated.quickstart.base.listeners.OnNavigateListener;
import com.github.appreciated.quickstart.base.navigation.theme.PageHolder;
import com.github.appreciated.quickstart.base.navigation.theme.PagerView;
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
    private Component currentComponent;
    private OnNavigateListener listener;

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
        navigationElements.put(navigation.getClass(), navigation);
    }

    public void navigateTo(Page page) {
        if (currentPage != page) {
            if (currentPage != null) {
                currentPage.onSubpageFinish();
                if (currentPage != currentComponent && currentComponent instanceof Page) {
                    ((Page) currentComponent).onSubpageFinish();
                }
            }
            currentPage = page;
            if (!(currentPage instanceof AutonomousPage)) {
                setPageTitleVisibility(page.showTitle());
                navigatorView.setCurrentSearchNavigable(page instanceof HasSearch ? (HasSearch) page : null);
                setContextActions(page instanceof HasContextActions ? (HasContextActions) page : null);
                navigatorView.setCurrentContainerLabel(page.getNavigationName());
            }
            navigatorView.addPage(page);
            currentPage.onSubpageLoaded();
            if (currentPage != currentComponent && currentComponent instanceof Page) {
                ((Page) currentComponent).onSubpageLoaded();
            }
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
                Page instance = classKey.getConstructor().newInstance();
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
        Page element = navigationElements.get(classKey);
        navigateTo(element);
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

    public Page getCurrentPage() {
        return currentPage;
    }

    public Component getCurrentComponent() {
        return currentComponent;
    }

    public static Page getCurrentSubpage() {
        return QuickStartUI.getStateManager().getCurrentPage();
    }

    public void initNavigationElements(Stream<Page> subpages) {
        subpages.forEach(subpage -> addNavigation(subpage));
    }

    @Override
    public void onFinish() {
        if (this.currentComponent instanceof FinishablePage.FinishListener) {
            ((FinishablePage.FinishListener) this.currentComponent).onFinish();
        }
    }

    @Override
    public void onUpdate() {
        if (this.currentComponent instanceof ContextActionListener) {
            ((ContextActionListener) this.currentComponent).onUpdate();
        }
    }
}
