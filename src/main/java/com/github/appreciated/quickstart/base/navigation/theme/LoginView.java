package com.github.appreciated.quickstart.base.navigation.theme;

import com.github.appreciated.quickstart.base.pages.Subpage;
import com.vaadin.ui.Component;

/**
 * Created by appreciated on 15.12.2016.
 */
public interface LoginView extends Component, Subpage, Login {
    void initTitle(String title);
}
