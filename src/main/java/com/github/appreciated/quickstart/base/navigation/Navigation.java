package com.github.appreciated.quickstart.base.navigation;

import com.github.appreciated.quickstart.base.components.DownloadButton;
import com.github.appreciated.quickstart.base.components.UploadButton;
import com.github.appreciated.quickstart.base.container.NavigablePagerView;
import com.github.appreciated.quickstart.base.container.NavigationContainerView;
import com.github.appreciated.quickstart.base.interfaces.*;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.ui.*;

import javax.xml.ws.Holder;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by appreciated on 10.12.2016.
 * <p>
 * <p>
 * The Navigation stores the instances of all the elements the were already called once and allows the programmer to only use one method
 */
public class Navigation {

    private final Component navigatorView;
    private final boolean isMobile;
    private AbstractComponentContainer contextButtonContainer;
    private AbstractComponentContainer smallContextButtonContainer;
    private ContextButtonClickListener contextButtonListener;
    private Component currentTab = null;
    private Button contextButton = null;
    private HorizontalLayout holder = null;

    Map<Class<? extends Navigable>, Map.Entry<Navigable, Component>> navigationElements = new HashMap<>();
    private Component currentComponent;
    private OnNavigateListener listener;
    private boolean isExpandButton = false;
    private Button.ClickListener clickListener;

    /**
     * @param navigatorView               The Component in which the User can navigate
     * @param contextButton               The Button Component which holds the main
     * @param holder
     * @param contextButtonContainer
     * @param smallContextButtonContainer
     */
    public Navigation(Component navigatorView, Button contextButton, HorizontalLayout holder, AbstractComponentContainer contextButtonContainer, AbstractComponentContainer smallContextButtonContainer) {
        this.contextButton = contextButton;
        this.holder = holder;
        this.navigatorView = navigatorView;
        this.contextButtonContainer = contextButtonContainer;
        this.smallContextButtonContainer = smallContextButtonContainer;
        isMobile = NavigationUI.isMobile();
    }

    public void addNavigation(Button tab, Navigable navigation) {
        navigationElements.put(navigation.getClass(), new AbstractMap.SimpleEntry<>(navigation, tab));
        tab.addClickListener(event -> navigateTo(tab, navigation));
    }

    public void navigateTo(Component tab, Navigable navigableComponent) {
        if (navigableComponent instanceof ContextNavigable) {
            initContextButtons((ContextNavigable) navigableComponent);
        } else {
            setStyle(contextButtonContainer, "display-none", true);
            setStyle(smallContextButtonContainer, "display-none", true);
        }

        if (currentTab != tab) {
            refreshTab(tab);
            if (navigableComponent instanceof PagerNavigable) {
                navigateTo((PagerNavigable) navigableComponent);
            } else if (navigableComponent instanceof ContainerdNavigable) {
                navigateTo((ContainerdNavigable) navigableComponent);
            } else {
                navigateTo(navigableComponent);
            }
        }
    }

    private void initContextButtons(ContextNavigable navigable) {
        smallContextButtonContainer.removeAllComponents();
        List<Action> contextIcons = navigable.getContextIcons();
        List<HashMap.SimpleEntry<Resource, Component>> generatedButtons = new ArrayList<>();

        if (contextIcons.size() > 1) {
            this.isExpandButton = true;
            contextButton.setIcon(FontAwesome.ELLIPSIS_V);
            contextIcons.stream().forEach(action -> {
                Component buttonComponent = null;
                switch (action.getActionType()) {
                    case DOWNLOAD:
                        buttonComponent = new DownloadButton(action);
                        ((DownloadButton) buttonComponent).addClickListener(clickEvent -> navigable.onContextButtonClicked(action.getResource()));
                        break;
                    case BUTTON:
                        Button button = new Button(action.getResource());
                        button.addClickListener(clickEvent -> navigable.onContextButtonClicked(action.getResource()));
                        button.addStyleName("small-context-button");
                        buttonComponent = button;
                        break;
                    case UPLOAD:
                        buttonComponent = new UploadButton(action);
                        break;
                }
                smallContextButtonContainer.addComponent(buttonComponent);
                generatedButtons.add(new AbstractMap.SimpleEntry<>(action.getResource(), buttonComponent));
            });
        } else {
            this.isExpandButton = false;
            contextButton.setIcon(contextIcons.get(0).getResource());
            generatedButtons.add(new AbstractMap.SimpleEntry<>(contextIcons.get(0).getResource(), contextButton));
        }
        setStyle(contextButtonContainer, "display-none", false);

        if (clickListener != null) {
            contextButton.removeClickListener(clickListener);
        }
        clickListener = (Button.ClickListener) clickEvent -> {
            if (smallContextButtonContainer != null && !isExpandButton) {
                contextButtonListener.onContextButtonClick(contextButton.getIcon());
            } else if (isExpandButton) {
                toggleStyle(smallContextButtonContainer, "display-none");
            }
        };
        contextButton.addClickListener(clickListener);
        navigable.generatedButtons(generatedButtons);
    }

    public void refreshTab(Component tab) {
        String style = isMobile ? "mobile-tab-active" : "tab-active";

        if (tab != null) {
            tab.addStyleName(style);
            if (currentTab != null) {
                currentTab.removeStyleName(style);
            }
            currentTab = tab;
        }
    }

    public void navigateTo(ContainerdNavigable component) {
        NavigationContainerView container = new NavigationContainerView();
        container.getContentHolder().addComponent(component);
        container.getLabelHolder().setVisible(false);
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

    public Component getNavigatorView() {
        return navigatorView;
    }

    public static void next() {
        ((NavigationUI) UI.getCurrent()).getNavigation().nextPagerView();
    }

    public static void last() {
        ((NavigationUI) UI.getCurrent()).getNavigation().lastPagerView();
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
