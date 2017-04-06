package com.github.appreciated.quickstart.base.navigation.container;

import com.github.appreciated.quickstart.base.navigation.actions.Action;
import com.github.appreciated.quickstart.base.navigation.actions.CustomAction;
import com.github.appreciated.quickstart.base.navigation.interfaces.*;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Johannes on 01.04.2017.
 */
public abstract class SubPageNavigator extends VerticalLayout implements Subpage, HasSubpages, HasContextActions {

    private MenuBar menuBar;
    private List<Action> subpageActions = new ArrayList<>();
    private Map<Subpage, MenuBar.MenuItem> menuBarItems = new HashMap<>();
    private String standardStyle;


    public SubPageNavigator() {
        menuBar = new MenuBar();
        getPagingElements().getSubpages()
                .forEach(subpage -> addSubpage(subpage));
        this.setMargin(false);
        menuBar.setStyleName("borderless navigation-bar");
        setCurrentSubpage(getPagingElements().first());
    }

    public void setCurrentSubpage(Subpage page) {
        this.removeAllComponents();
        subpageActions.clear();
        if (page instanceof HasContextActions) {
            subpageActions.addAll(((HasContextActions) page).getContextActions());
        }
        if (page instanceof ContainerSubpage) {
            NavigationContainerView container = new NavigationContainerView();
            if (((ContainerSubpage) page).hasPadding()) {
                container.getContentHolder().addStyleName("container-padding");
            }
            boolean hasPercentageHeight = page instanceof HasPercentageHeight;
            if (hasPercentageHeight) {
                container.setSizeFull();
                container.getContentHolder().setSizeFull();
            }
            container.getContentHolder().addComponent(page);
            this.addComponent(container);
        } else {
            this.addComponent(page);
        }
        updateContextActions();
        menuBarItems.forEach((subpage, menuItem) -> menuItem.setStyleName(page.equals(subpage) ? getStyleName() + "active" : standardStyle));
    }

    @Override
    public boolean showTitle() {
        return false;
    }

    @Override
    public List<Action> getContextActions() {
        ArrayList<Action> list = new ArrayList<>();
        list.add(new CustomAction(menuBar).withInsertLeft(true).withAlignment(Alignment.MIDDLE_LEFT));
        list.addAll(subpageActions);
        return list;
    }

    public void addSubpage(Subpage page) {
        MenuBar.MenuItem item = menuBar.addItem(page.getNavigationName(), page.getNavigationIcon(), (MenuBar.Command) menuItem -> setCurrentSubpage(page));
        standardStyle = item.getStyleName();
        menuBarItems.put(page, item);
    }

    public void removeSubpage(Subpage page) {
        menuBar.removeItem(menuBarItems.get(page));
        menuBarItems.remove(page);
    }

    public void setCaption(Subpage page, String name) {
        menuBarItems.get(page).setText(name);
    }
}