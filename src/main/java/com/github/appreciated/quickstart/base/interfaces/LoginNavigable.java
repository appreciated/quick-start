package com.github.appreciated.quickstart.base.interfaces;

import com.github.appreciated.quickstart.base.login.AccessControl;

/**
 * Created by appreciated on 15.12.2016.
 */
public interface LoginNavigable extends Navigable {
    void setAuthenticationListener(LoginListener loginListener);

    AccessControl getAccessControl();
}
