package com.github.appreciated.quickstart.base.navigation.interfaces;

import com.github.appreciated.quickstart.base.authentication.login.AccessControl;
import com.github.appreciated.quickstart.base.authentication.login.LoginListener;
import com.github.appreciated.quickstart.base.navigation.RegistrationControl;
import com.vaadin.ui.Component;

/**
 * Created by appreciated on 15.12.2016.
 */
public interface LoginPage extends Component, Subpage {
    void setAuthenticationListener(LoginListener loginListener);
    void initWithAccessControl(AccessControl accessControl);
    void initRegistrationControl(RegistrationControl accessControl);
    void initTitle(String title);
}
