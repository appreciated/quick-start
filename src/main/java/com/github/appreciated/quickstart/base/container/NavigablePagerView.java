package com.github.appreciated.quickstart.base.container;


import com.github.appreciated.quickstart.base.interfaces.ContainerdNavigable;
import com.github.appreciated.quickstart.base.interfaces.Navigable;
import com.github.appreciated.quickstart.base.interfaces.PagerNavigable;
import com.github.appreciated.quickstart.base.listeners.LayoutLeftClickListener;
import com.vaadin.shared.ui.label.ContentMode;
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

    private final List<Navigable> navigables;
    private PagerNavigable navigatable;

    private final Map<Navigable, Label> navigablesMap = new HashMap<>();

    private Navigable currentElement;

    public NavigablePagerView(PagerNavigable navigatable) {
        this.navigables = navigatable.getPagingElements();
        this.navigatable = navigatable;
        pagerDots.removeAllComponents();
        for (Navigable navigable : navigables) {
            HorizontalLayout layout = new HorizontalLayout();
            layout.setHeight(100, Unit.PERCENTAGE);
            Label label = new Label("<li><a></a></li>", ContentMode.HTML);
            label.setStyleName("pager");
            layout.addComponent(label);
            layout.addLayoutClickListener(new LayoutLeftClickListener(event -> navigateTo(navigable)));
            layout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
            pagerDots.addComponent(layout);
            navigablesMap.put(navigable, label);
        }
        next.addClickListener(event -> next());
        last.addClickListener(event -> last());
        navigateTo(navigables.get(0));
        navigablesMap.get(navigables.get(0)).addStyleName("pager-current");
    }

    private void navigateTo(Navigable navigable) {
        if (currentElement != null) {
            navigablesMap.get(currentElement).removeStyleName("pager-current");
        }
        navigablesMap.get(navigable).addStyleName("pager-current");
        currentElement = navigable;
        int index = navigables.indexOf(navigable);
        last.setVisible(index != 0);
        next.setVisible(index != navigables.size() - 1);
        if (navigable instanceof ContainerdNavigable) {
            NavigationContainerDesign container = new NavigationContainerDesign();
            container.content.addComponent(navigable);
            container.container_title.setVisible(false);
            content.removeAllComponents();
            content.addComponent(container);
        } else {
            content.removeAllComponents();
            content.addComponent(navigable);
        }
    }

    public void next() {
        if (navigables.indexOf(currentElement) == navigables.size() - 1) {
            navigatable.onFinish();
        } else {
            navigateTo(navigables.get(navigables.indexOf(currentElement) + 1));
        }
    }

    public void last() {
        navigateTo(navigables.get(navigables.indexOf(currentElement) - 1));
    }
}
