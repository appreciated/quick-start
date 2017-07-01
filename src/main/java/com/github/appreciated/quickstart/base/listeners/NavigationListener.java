package com.github.appreciated.quickstart.base.listeners;

import com.github.appreciated.quickstart.base.pages.Page;

/**
 * Created by appreciated on 28.06.2017.
 */
public interface NavigationListener {
    default void onNavigate(Page next) {
    }

    void onDone();
}
