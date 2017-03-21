package com.github.appreciated.quickstart.base.navigation.interfaces;

import com.vaadin.ui.Component;

/**
 * Created by appreciated on 15.12.2016.
 */
public interface LoginPage extends Component, Subpage, Login {
    void initTitle(String title);
}
