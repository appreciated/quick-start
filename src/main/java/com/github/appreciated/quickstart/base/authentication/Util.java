package com.github.appreciated.quickstart.base.authentication;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;

/**
 * Created by appreciated on 15.12.2016.
 */
public class Util {
    public static void invalidateSession() {
        VaadinSession.getCurrent().getSession().invalidate();
        Page.getCurrent().reload();
    }
}
