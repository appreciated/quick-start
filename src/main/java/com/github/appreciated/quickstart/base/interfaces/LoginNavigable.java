package com.github.appreciated.quickstart.base.interfaces;

import com.github.appreciated.quickstart.base.login.AccessControl;
import com.github.appreciated.quickstart.base.navigation.RegistrationControl;

/**
 * Created by appreciated on 15.12.2016.
 */
public interface LoginNavigable extends Navigable {
    void setAuthenticationListener(LoginListener loginListener);
    void initWithAccessControl(AccessControl accessControl);
    void initRegistrationControl(RegistrationControl accessControl);
    void initTitle(String title);
}
