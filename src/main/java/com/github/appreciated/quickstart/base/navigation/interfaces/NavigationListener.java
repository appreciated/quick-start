package com.github.appreciated.quickstart.base.navigation.interfaces;

import com.github.appreciated.quickstart.base.navigation.interfaces.base.Subpage;

/**
 * Created by appreciated on 28.06.2017.
 */
public interface NavigationListener {
    default void onNavigate(Subpage next) {
    }

    void onDone();
}
