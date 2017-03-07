package com.github.appreciated.quickstart.base.container;


import com.github.appreciated.quickstart.base.listeners.LayoutLeftClickListener;
import com.github.appreciated.quickstart.base.navigation.interfaces.ContainerPage;
import com.github.appreciated.quickstart.base.navigation.interfaces.Page;
import com.github.appreciated.quickstart.base.navigation.interfaces.Pager;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by appreciated on 09.12.2016.
 */
public class NavigablePagerView extends NavigationPagerDesign {

    private final List<Page> pages;
    private Pager navigatable;

    private final Map<Page, Label> navigablesMap = new HashMap<>();

    private Page currentElement;

    public NavigablePagerView(Pager navigatable) {
        this.pages = navigatable.getPagingElements();
        this.navigatable = navigatable;
        pagerDots.removeAllComponents();
        for (Page page : pages) {
            HorizontalLayout layout = new HorizontalLayout();
            layout.setHeight(100, Unit.PERCENTAGE);
            Label label = new Label("<li><a></a></li>", ContentMode.HTML);
            label.setStyleName("pager");
            layout.addComponent(label);
            layout.addLayoutClickListener(new LayoutLeftClickListener(event -> navigateTo(page)));
            layout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
            pagerDots.addComponent(layout);
            navigablesMap.put(page, label);
        }
        next.addClickListener(event -> next());
        last.addClickListener(event -> last());
        navigateTo(pages.get(0));
        navigablesMap.get(pages.get(0)).addStyleName("pager-current");
    }

    private void navigateTo(Page page) {
        if (currentElement != null) {
            navigablesMap.get(currentElement).removeStyleName("pager-current");
        }
        navigablesMap.get(page).addStyleName("pager-current");
        currentElement = page;
        int index = pages.indexOf(page);
        last.setVisible(index != 0);
        next.setVisible(index != pages.size() - 1);
        if (page instanceof ContainerPage) {
            NavigationContainerDesign container = new NavigationContainerDesign();
            container.content.addComponent(page);
            content.removeAllComponents();
            content.addComponent(container);
        } else {
            content.removeAllComponents();
            content.addComponent(page);
        }
    }

    public void next() {
        if (pages.indexOf(currentElement) == pages.size() - 1) {
            navigatable.onFinish();
        } else {
            navigateTo(pages.get(pages.indexOf(currentElement) + 1));
        }
    }

    public void last() {
        navigateTo(pages.get(pages.indexOf(currentElement) - 1));
    }
}
